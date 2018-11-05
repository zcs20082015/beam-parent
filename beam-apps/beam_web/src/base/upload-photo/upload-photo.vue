<template>
  <!-- 上传图片弹框 -->
    <el-dialog class="photo-dialog" title="本地上传" :visible.sync="my_show">
      <el-form>
        <el-form-item label="网络图片" :label-width="formLabelWidth">
          <div class="dialog-row">
            <el-input class="dialog-input" auto-complete="off" size="small" v-model="url"></el-input>
            <el-button @click="confirmWithUrl" size="small" plain>提取</el-button>
          </div>

        </el-form-item>
        <el-form-item label="本地图片" :label-width="formLabelWidth">
          <div>
            <ul class="xs-upload-list">
              <li class="xs-upload-list__item" v-for="item, index in pics">
                <img :src="item" alt="" class="xs-upload-list__item-thumb">
                <span class="xs-upload-list__item-actions" @click.stop="deletePhoto(index)"><!---->
                  <span class="xs-upload-list__item-delete"><i class="el-icon-delete"></i></span>
                </span>
              </li>

            </ul>
            <div tabindex="0" class="xs-upload" v-show="can_add">
              <i class="el-icon-plus"></i>
              <input type="file" name="file" accept="image/jpeg, image/png, image/gif" :multiple="is_mutiple" class="xs-upload__input" ref="file">
            </div>
          </div>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="cancel" size="small">取 消</el-button>
          <el-button type="primary" @click="confirm" size="small">确 定</el-button>
        </span>
    </el-dialog>

</template>

<script>
  export default {
    props: {
      show: {
        type: Boolean,
        default: false
      },
      limit: {
        type: Number,
        default: 1
      },
      limit_max: { // 限制是否最多是limit
        type: Boolean,
        default: true,
      },
      current_len: {
        type: Number,
        default: 0
      },
      multity: {
        type: String,
        default: ""
      },
      action: {
        type: String,
        default: 'add'
      },
      reset: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        formLabelWidth: '100px',
        binded: false,
        pics: [], // 此次操作的图片
        can_add: true,
        len: this.current_len, // 当前总长度
        prev_len: null,
        url: null,
        my_show: this.show,
        multity_len_map: {},
        is_mutiple: true,
      }
    },

    mounted() {

    },

    updated() {

      if (!this.show) return;

      if (!this.binded && this.$refs.file) {
        this.binded = true;
        let limit = this.limit;
        let _this = this;

        this.$refs.file.onchange = function() {
          let need_len = this.files.length;

          if (_this.limit_max) {
            let len = _this.multity === "" ? _this.len : _this.multity_len_map[_this.multity];

            if (this.files.length >= limit - len) {  // 已经超出limit
              need_len = limit - len;
              _this.can_add = false;

              if (this.files.length !== need_len)
                _this.$message.warning('已自动过滤 ' + (this.files.length - need_len) + ' 张');
            }

          }else {
              need_len = need_len > limit ? limit : need_len
          }

          for (let i = 0; i < need_len; i ++) {

            let FR= new FileReader();
            FR.addEventListener("load", function(e) {
              let base64Img = e.target.result;
              _this.pics.push(base64Img);

              if (i === need_len - 1)
                _this.multity === '' ? (_this.len += need_len) : (_this.multity_len_map[_this.multity] += need_len);  // 更新长度
//                _this.can_add = false;
                _this.$refs.file.value = null;

            });

            FR.readAsDataURL(this.files[i]);
          }

        }
      }
    },

    methods: {
      cancel() {
        this.pics = [];
        this.prev_len && (this.len = this.prev_len);
        this.$emit('update:show', false);
      },
      confirm() {
        this.$emit('confirm', this.pics);
        this.$emit('update:show', false);

        this.pics = []
      },

      confirmWithUrl() {
        if (this.url) {
          this.multity === '' ? this.len += 1 : this.multity_len_map[this.multity] += 1;
          this.$emit('update:show', false);
          this.$emit('confirm', [this.url]);
        }else {
            this.$message.error('请输入要提取图片的地址')
        }

      },

      deletePhoto(index) {
        this.pics.splice(index, 1);
        this.multity === '' ? this.len -= 1 : this.multity_len_map[this.multity] -= 1;
        this.can_add = true;
      }
    },

    watch: {
      current_len(val) {
        this.len = val;
      },

      show(val) {

        this.my_show = val;

        if (!this.limit_max) {
            this.can_add = true
        }
        else if(val) {

          if (this.reset) {
            this.multity === "" ? this.len = 0 : this.multity_len_map[this.multity] = 0;
            this.is_mutiple = true;
            this.can_add = true;
            this.$emit('update:reset', false);
            return;
          }

          if (this.action === 'change') {
            this.is_mutiple = false;

            this.multity === "" ? this.len = 0 : this.multity_len_map[this.multity] = 0;

            if (this.limit > 1) {
              this.$message.error('超过1张不可修改');
            }else {

              this.can_add = true;
            }
          }else {
            console.log('add')
            this.is_mutiple = true;
            if (this.multity === "") {
              if (this.len >= this.limit) {
                this.can_add = false;
                return;
              }
            }else {
              !this.multity_len_map[this.multity] && (this.multity_len_map[this.multity] = 0)

              if (this.multity_len_map[this.multity] >= this.limit) {
                this.can_add = false;
                return;
              }
            }

            this.can_add = true;
          }
        }
      },

      my_show(val) {
        if (!val) {
          this.cancel();
          this.$emit('update:show', false);
        }

      }
    }

  }
</script>


<style lang="scss" scoped>
  @import "../../common/style/adapter.config";
  @import "../../common/style/variable.config";

  .photo-dialog {
    .dialog-row {
      @include flex($al: center);
      .dialog-input {
        margin-right: 10px;
      }
    }
  }

  .el-form-item {
    margin-bottom: 22px !important;
  }

  .xs-upload-list {
    margin: 0;
    display: inline;
    vertical-align: top;

    .xs-upload-list__item {
      position: relative;
      overflow: hidden;
      background-color: #fff;
      border: 1px solid #c0ccda;
      border-radius: 6px;
      -webkit-box-sizing: border-box;
      box-sizing: border-box;
      width: $upload_photo_item_size;
      height: $upload_photo_item_size;
      margin: 0 8px 8px 0;
      display: inline-block;

      .xs-upload-list__item-thumb {
        width: 100%;
        height:100%;
      }

      .xs-upload-list__item-actions {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0;
        top: 0;
        cursor: default;
        text-align: center;
        color: #fff;
        opacity: 0;
        font-size: 20px;
        background-color: rgba(0,0,0,.5);
        -webkit-transition: opacity .3s;
        transition: opacity .3s;
        @include flex($al: center, $jc: center);

        &:hover {
          opacity: 1;
        }
      }
    }


  }

  .xs-upload {
    background-color: #fbfdff;
    border: 1px dashed #c0ccda;
    border-radius: 6px;
    box-sizing: border-box;
    width: $upload_photo_item_size;
    height: $upload_photo_item_size;
    cursor: pointer;
    vertical-align: top;
    display: inline-block;
    text-align: center;
    outline: none;
    position: relative;
    line-height: $upload_photo_item_size + 8px;

    i {
      font-size: 28px;
      color: #8c939d;
    }
    .xs-upload__input {
      position: absolute;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      opacity: 0;
      display: block;
    }
  }
</style>
