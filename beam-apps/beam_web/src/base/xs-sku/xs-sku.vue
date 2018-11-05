<template>
  <div>

    <div class="xs-sku">
      <div>
        <div class="xs-sku-group" v-for="(item, index) in sku_group_list">
          <h3 class="group-title">
            <span class="group-title__label">规格名:</span>
            <div class="xs-popover-wrapper xs-select" :id="'auto_id' + item.id" :data-index="index" @click.stop="showSkuTitleDrop">
              <div class="xs-select-text">
                {{item.name ? item.name : '请选择：按回车自定义'}}
              </div>
            </div>
            <el-checkbox v-if="index === 0 && showAttrBtn" v-model="item.add_img" style="margin-left: 10px;" class="xs-checkbox"><span class="xs-checkbox__label">添加规格图片</span></el-checkbox>
            <a class="remove-sku-group" v-if="showAttrBtn" @click="deleteSku(index)">×</a>
          </h3>

          <div class="group-container">
            <span class="sku-list__label">规格值：</span>
            <div>
              <div class="xs-sku__list">
                <div class="xs-sku-item" @click="skuNameDidEdit(index,j)" :style="{'margin-bottom': item.add_img ? '100px' : '0' }" v-for="subItem, j in item.attrs">
                  <span >{{subItem.name}}</span>
                  <div class="item-remove" @click.stop="skuNameDidDelete(index, j)" v-if="showAttrBtn">×</div>
                  <div class="upload-img-wrap" v-if="item.add_img">
                    <div class="arrow"></div>
                    <div class="upload-img">
                      <span class="item-remove small" @click.stop="deletePhoto(j)" v-show="subItem.pic" title="删除">×</span>
                      <img v-if="subItem.pic" :src="subItem.pic" @click.stop>
                      <div v-else @click.stop="uploadDidClick(j)" class="item-upload-container" style="position: relative;"><div class="add-image js-btn-add">+</div></div>
                      <div class="rc-upload" v-show="subItem.pic">
                        <div class="img-edit">
                          <span style="margin: 0 auto;" @click.stop="replacePhoto(j)">替换</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <span class="xs-sku-add" v-if="showAttrBtn" @click="showUIPopover" :data-index="index">+ 添加</span>
              </div>
              <p class="xs-assist-label" style="line-height: 1.5;margin-top: 20px" v-if="item.add_standard_img">
                目前只支持为第一个规格设置不同的规格图片设置后,<br/> 用户选择不同规格会显示不同图片。建议尺寸：750X750（像素）
              </p>
            </div>
          </div>
          <div class="sku-group-cont"></div>
        </div>
        <div class="xs-sku-group" v-if="showAttrBtn">
          <h3 class="group-title">
            <el-button size="mini"  @click="addGroupList">添加规格项目</el-button>
          </h3>
        </div>
        <p class="xs-assist-label" style="line-height: 1.5;margin-top: 20px;color: red;" v-if="!showAttrBtn">
          温馨提示：正在进行中的拼团或砍价商品，不能修改规格！！！
        </p>
      </div>
    </div>

    <!--选择规格名-->
    <div class="xs-popover-mask" v-show="is_show_drop_down" @click="closeSkuTitleDrop"></div>
    <div class="xs-popover xs-select" v-show="is_show_drop_down" :style="{ position: 'absolute', left: drop_down_left + 'px', top: drop_down_top + 'px' }">
      <div class="xs-popover-content">
        <div class="xs-select-popup">
          <div class="xs-select-search">
            <input type="text" class="xs-select-filter" v-model="temp_sku_input_name" @keydown.enter="skuTitleDidClick(temp_sku_input_name)" v-focus="focusSearch">
          </div>
          <span v-for="item in sku_titles" @click="skuTitleDidClick(item)" class="xs-select-option">{{item}}</span>
        </div>
      </div>
    </div>

    <!--添加规格值面板-->
    <div class="ui-popover" v-show="is_show_ui_popover" :style="{ left: ui_popover_left + 'px', top: ui_popover_top + 'px'}">
      <div class="ui-popover-inner" :style="{width: ui_popover_width + 'px'}">

        <div class="select-container ">
          <ul class="select-choices">
            <li class="select-search-choice" v-for="item, index in temp_of_sub_sku_items" @click="deleteSubSkuOf(index)">
              {{item}}
              <a href="#" onclick="return false;" class="select-search-choice-close" tabindex="-1"></a>
            </li>
            <li class="select-search-field">
              <input type="text" placeholder="添加规格值" v-focus="focusPopover" @keydown.enter="addSubSkuOf" maxlength="20" v-model="adding_of_sub_name">
            </li>
          </ul>
          <div class="select-drop" v-show="adding_of_sub_name && adding_of_sub_name.length > 0">
            <ul class="select-results">
              <li @click="addSubSkuOf" class="select-highlighted">
                <div class="select-result-label">回车或点击来添加规格值: <i>{{adding_of_sub_name}}</i></div>
              </li>
            </ul>
          </div>

        </div>
        <div class="ui-btn ui-btn-primary" style="vertical-align: top" @click.stop="confirmSubSku">确定</div>
        <div class="ui-btn" style="vertical-align: top" @click="is_show_ui_popover=false">取消</div>

      </div>
      <div class="arrow"></div>

    </div>

    <!--编辑规格值面板-->
    <div class="ui-popover" v-show="is_show_ui_edit_popover" :style="{ left: ui_popover_edit_left + 'px', top: ui_popover_edit_top + 'px'}">
      <div class="ui-popover-inner" :style="{width: ui_popover_edit_width + 'px'}">

        <div class="select-container " style="width: 192px;">
          <ul class="select-choices">
            <li class="select-search-field">
              <input type="text" placeholder="修改规格值" @keydown.enter="confirmSubSkuDidEdit" v-focus="focusPopover" maxlength="20" v-model="temp_edit_name">
            </li>
          </ul>


        </div>
        <div class="ui-btn ui-btn-primary" @click="confirmSubSkuDidEdit" style="vertical-align: top">确定</div>
        <div class="ui-btn" style="vertical-align: top" @click.stop="is_show_ui_edit_popover=false">取消</div>

      </div>
      <div class="arrow"></div>

    </div>


    <upload-photo
      :limit="1"
      :multity="'index' + (current_upload_sub_item_index + 1)"
      :show.sync="showPhotoDialog"
      :action="photo_action"
      :reset.sync="need_reset"
      @confirm="confirmPhoto"
    ></upload-photo>

  </div>

</template>

<script>
  import UploadPhoto from 'base/upload-photo/upload-photo.vue';

  export default {
    name: 'xs-sku',
    components: {UploadPhoto},
    props: {
      showAttrBtn: {
        type: Boolean,
        default: true,
      },
      sku_group_list_prop: {
        type: Array,
        default: []
      }
    },
    data() {
        return {
          showPhotoDialog: false,
//        sku title name
          sku_titles: ['颜色',
            '尺寸',
            '尺码',
            '规格',
            '款式',
            '净含量',
            '种类'
          ],
          sku_title: '',

//        一组sku列表
          sku_group_list: [],
          group_primary_key: 0,
          drop_down_left: 0,
          drop_down_top: 0,
          sel_sku_group_index: 0,
          focusSearch: 0,
          sku_pic_maps: {},
          current_upload_sub_item_index: null,  // 即将上传图片的规格索引
          current_upload_sub_item_id: -1,
          photo_action: 'add',
          need_reset: false,

//        规格名
          temp_sku_input_name: null,

//        添加规格值相关
          is_show_ui_popover: false,
          ui_popover_left: 0,
          ui_popover_top: 0,
          ui_popover_width: 200,
          adding_of_sub_name: '',
          temp_of_sub_sku_items: [],
          add_sub_of_sku_group: null,
          focusPopover: false,


//        编辑规格值相关
          is_show_ui_edit_popover: false,
          ui_popover_edit_left: 0,
          ui_popover_edit_top: 0,
          ui_popover_edit_width: 200,
          temp_edit_name: null,
          temp_edit_index_path: null,  // 将要编辑的规格值位置
        }
    },

    methods: {

      // 添加一组新的规格
      addGroupList() {
        let sku_group = {
          name: null,
          add_img: false, // 是否添加图片
          show_drop_down: false,
          show_ui_popover: false,
          id: this.group_primary_key,
          attr_temps: [],
          attrs: []
        }

        this.sku_group_list.push(sku_group);
        this.group_primary_key ++

        setTimeout(() => {
          this.showSkuTitleDrop({
            currentTarget: document.getElementById('auto_id' + sku_group.id)
          })
        }, 1)
      },

      showSkuTitleDrop(event) {
        if (!this.showAttrBtn) {
          return
        }
        let target = event.currentTarget;
        var parent = target.parentNode;
        let rect = target.getBoundingClientRect()
        this.drop_down_left = target.offsetLeft + parent.offsetLeft;
        this.drop_down_top = rect.height + target.offsetTop + parent.offsetTop;

        let index = parseInt( target.getAttribute('data-index'));
        this.sel_sku_group_index = index
        this.sku_group_list[index].show_drop_down = true
        this.focusSearch = true

      },

      closeSkuTitleDrop() {
        this.focusSearch = false;
        this.sku_group_list[this.sel_sku_group_index].show_drop_down = false;
      },


//      规格名选择
      skuTitleDidClick(name) {
        this.temp_sku_input_name = null;
        let sku_group = this.sku_group_list[this.sel_sku_group_index];
        var findName = this.sku_group_list.find((item) => item.name === name);
        if (findName) {
          this.$message.error('已经添加了相同的规格名');
          return ;
        }

        sku_group.name = name;
        sku_group.show_drop_down = false;

        this.focusSearch = false
      },


//      显示添加规格值
      showUIPopover(event) {
        this.closeAllPopover();
        let half_popover_width = (this.ui_popover_width + 40) * 0.5;
        this.is_show_ui_popover = true;
        let target = event.currentTarget;
        let parent = target.parentNode;
        let rect = target.getBoundingClientRect();
        this.ui_popover_left = target.offsetLeft + rect.width * 0.5 - half_popover_width;
        this.ui_popover_top = rect.height + parent.offsetTop + 10;

        let index = target.getAttribute('data-index');
        this.add_sub_of_sku_group = this.sku_group_list[index];
        this.focusPopover = true
      },

//      添加规格值
      addSubSkuOf: function() {

        if (this.adding_of_sub_name === null || this.adding_of_sub_name.length === 0) return;
        if (this.temp_of_sub_sku_items.indexOf(this.adding_of_sub_name) === -1)
          this.temp_of_sub_sku_items.push(this.adding_of_sub_name)
        else {
          this.$message.error('已经添加了相同的规格名');
        }
        this.adding_of_sub_name = null
        this.focusPopover = true
      },

//      删除
      deleteSku(index) {
        this.sku_group_list.splice(index, 1)

        this.$emit('update', {need_clear: true, sku_group_list: this.sku_group_list})
      },

//      在添加面板中删除规格值
      deleteSubSkuOf(index) {
        this.temp_of_sub_sku_items.splice(index, 1)
      },

//      确认添加规格值
      confirmSubSku() {
        this.add_sub_of_sku_group.attr_temps = Array.from(new Set(this.add_sub_of_sku_group.attr_temps.concat(this.temp_of_sub_sku_items)));
        var temp_attrs_obj = this.add_sub_of_sku_group.attr_temps.map((elm) => {return {name:elm, pic: this.sku_pic_maps[elm] ? this.sku_pic_maps[elm] : ''}})
        this.add_sub_of_sku_group.attrs = temp_attrs_obj;
        this.is_show_ui_popover = false;
        this.temp_of_sub_sku_items = [];
//        this.updateSkuTable()

        this.$emit('update', {sku_group_list: this.sku_group_list});
      },
//      编辑规格值
      skuNameDidEdit(i, j) {
        if (!this.showAttrBtn) {
          return
        }
        this.closeAllPopover();

        let half_popover_width = (this.ui_popover_edit_width + 40) * 0.5
        let target = event.currentTarget;
        let parent = target.parentNode;
        let rect = target.getBoundingClientRect();
        this.ui_popover_edit_left = target.offsetLeft + rect.width * 0.5 - half_popover_width;
        this.ui_popover_edit_top = rect.height + parent.offsetTop + 20;

        this.is_show_ui_edit_popover = true;

        this.temp_edit_name = this.sku_group_list[i].attr_temps[j];
        this.temp_edit_index_path = {
          i: i,
          j: j
        }
      },
//      删除规格值
      skuNameDidDelete: function(i, j) {
        delete this.sku_pic_maps[ this.sku_group_list[i].attr_temps[j]];
        this.sku_group_list[i].attr_temps.splice(j, 1);
        this.sku_group_list[i].attrs.splice(j, 1);
        this.closeAllPopover();
//        this.updateSkuTable();
        this.$emit('update', {sku_group_list: this.sku_group_list});
      },

//      规格值确认修改
      confirmSubSkuDidEdit() {
        var i = this.temp_edit_index_path.i,
          j = this.temp_edit_index_path.j;

        this.sku_group_list[i].attr_temps[j] = this.temp_edit_name;
        this.sku_group_list[i].attrs[j].name=this.temp_edit_name;

//        this.updateSkuTable();
        this.$emit('update', {sku_group_list: this.sku_group_list});

        this.closeAllPopover();
      },

//      图片上传
      uploadDidClick(index) {
        this.photo_action = 'add';
        this.showPhotoDialog = true;
        this.current_upload_sub_item_index = index;
      },

      replacePhoto(index) {
        this.photo_action = 'change';
        this.showPhotoDialog = true;
        this.current_upload_sub_item_index = index;
      },

      deletePhoto(index) {
        this.sku_group_list[0].attrs[index].pic = null;
        this.need_reset = true;
      },

      closeAllPopover() {
        this.is_show_ui_edit_popover = false;
        this.is_show_ui_popover = false;
      },

      confirmPhoto(urls) {
        if (this.current_upload_sub_item_index === null) {
          this.$message.error('请选择要上传图片的规格');
          return;
        }

        this.sku_group_list[0].attrs[this.current_upload_sub_item_index].pic = urls[0]

        this.sku_pic_maps[ this.sku_group_list[0].attr_temps[this.current_upload_sub_item_index]] = urls[0]
        this.current_upload_sub_item_index = null;
      },

      getScrollTop() {
        let scrollTop=0;
        if(document.documentElement&&document.documentElement.scrollTop){
          scrollTop=document.documentElement.scrollTop;
        }else if(document.body){
          scrollTop=document.body.scrollTop;
        }
        return scrollTop;
      },

      getScrollLeft() {
        let scrollLeft=0;
        if(document.documentElement&&document.documentElement.scrollLeft){
          scrollLeft=document.documentElement.scrollLeft;
        }else if(document.body){
          scrollLeft=document.body.scrollLeft;
        }
        return scrollLeft;
      },

    },

    computed: {
      is_show_drop_down: function () {
        if (this.sel_sku_group_index === -1) {
          return false
        }

        if (this.sku_group_list[this.sel_sku_group_index]) {
          return this.sku_group_list[this.sel_sku_group_index].show_drop_down;
        }
        return false;
      },

    },

    watch: {
      sku_group_list_prop(val) {
          let copy_list = val.map(elm => Object.assign({}, elm))
        console.log(copy_list)
          this.sku_group_list = copy_list
          this.group_primary_key = val.length;
          this.$emit('update', { sku_group_list: this.sku_group_list})
      }
    }
  }
</script>

<style lang="scss">
  @import "~common/style/adapter.config";
  .xs-sku {
    background-color: #fff;
    padding: 10px;
    border: 1px solid #e5e5e5;

    .xs-checkbox__label, .sku-list__label, .group-title__label {
      font-size: 12px !important;
      font-weight: normal;
      min-width: 50px;
      display: inline-block;
      margin-right: 10px;
    }

    .xs-sku-group {
      .group-title {
        position: relative;
        padding: 7px 10px;
        margin: 0;
        background-color: #f8f8f8;
        font-size: 12px;
        line-height: 16px;
        font-weight: 400;

        @include flex($al: center);

        &:hover {
          .remove-sku-group {
            opacity: 1;
          }
        }

        .remove-sku-group {
          transition: opacity 0.25s;
          position: absolute;
          opacity: 0;
          top: 12px;
          right: 10px;
          color: #fff;
          text-align: center;
          cursor: pointer;
          width: 18px;
          height: 18px;
          font-size: 14px;
          line-height: 16px;
          background: rgba(153, 153, 153, 0.6);
          border-radius: 10px;
          text-indent: 0;
        }
      }

      .group-container {

        padding: 10px 10px 0;
        @include flex();

        .xs-sku__list {
          @include flex-full();
          @include flex($al: flex-start);
          @include flex-wrap();

          .xs-sku-item {
            border: 1px solid #AAA;
            padding: 2px 4px;
            display: inline-block;
            margin-right: 10px;
            margin-bottom: 5px;
            margin-top: 5px;
            width: 80px;
            vertical-align: middle;
            text-align: center;
            position: relative;
            border-radius: 4px;
            height: 22px;
            cursor: pointer;

            .upload-img-wrap {

              position: absolute;
              top: 36px;
              left: 0;
              padding: 2px;
              width: 84px;
              background: #fff;
              border-radius: 4px;
              border: 1px solid #dcdcdc;
              box-sizing: content-box;

              .upload-img {
                position: relative;
                width: 84px;
                height: 84px;
                img {
                  width: 100%;
                  height: 100%;
                }
              }

              .item-remove {
                position: absolute;
                z-index: 2;
                top: -9px;
                right: -9px;
                width: 20px;
                height: 20px;
                font-size: 16px;
                line-height: 18px;
                color: #fff;
                text-align: center;
                cursor: pointer;
                background: hsla(0,0%,60%,.6);
                border-radius: 10px;
                opacity: 0;
                transition: opacity 0.25s;

                &:hover {
                  background: hsla(0,0%,60%,1);
                }
              }

              &:hover {
                .img-edit {
                  display: block;
                }

                .item-remove {
                  opacity: 1;
                }
              }

              .img-edit {
                cursor: pointer;
                display: none;
                position: absolute;
                bottom: 0;
                left: 0;
                width: 100%;
                color: #fff;
                opacity: .5;
                background: #000;
              }

              .arrow {
                position: absolute;
                width: 0;
                height: 0;
                top: -5px;
                left: 44%;
                border-style: solid;
                border-color: transparent;
                border-left: 5px solid transparent;
                border-right: 5px solid transparent;
                border-bottom: 5px solid #000000;

                &::after {
                  position: absolute;
                  display: block;
                  width: 0;
                  height: 0;
                  border-color: transparent;
                  border-style: solid;
                  top: -10px;
                  margin-left: -10px;
                  border-bottom-color: #fff;
                  border-top-width: 0;
                  border-width: 10px;
                  content: "";
                }
              }

              .add-image {
                width: 84px;
                height: 84px;
                line-height: 84px;
                text-align: center;
                background: #fff;
                font-size: 30px;
                color: #e5e5e5;
                cursor: pointer;
              }
            }

            span {
              display: block;
              width: 74px;
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
              font-size: 12px;
              height: 22px;
              line-height: 22px;
            }

            &:hover {
              .item-remove {
                display: block;
              }
            }
            .item-remove {
              display: none;
              position: absolute;
              z-index: 2;
              top: -8px;
              right: -8px;
              width: 18px;
              height: 18px;
              font-size: 14px;
              line-height: 16px;
              border-radius: 9px;
              color: #fff;
              text-align: center;
              cursor: pointer;
              background: rgba(153, 153, 153, 0.6)
            }


          }
          .xs-sku-add {
            color: #38f;
            font-size: 12px;
            cursor: pointer;
            margin-left: 5px;
          }
        }
      }

      .sku-group-cont {
        padding: 0 10px 0 70px;
        margin-bottom: 15px;
        margin-top: 2px;
      }
    }
  }

  .xs-popover-mask {
    position: fixed;
    left:0;
    right:0;top: 0;
    bottom:0;
    z-index:101;
  }

  .xs-select-popup {
    -webkit-box-shadow: 0 1px 4px rgba(0,0,0,.2);
    box-shadow: 0 1px 4px rgba(0,0,0,.2);
    left: 0;
    outline: none;
    overflow-y: auto;
    position: relative;
    width: 100%;
    z-index: 2000;
    margin-top: 2px;
    background-color: #fff;
    max-height: 224px;

    .xs-select-search {
      position: relative;
      @include box-sizing();

      &:before {
        background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAMAAADXqc3KAAAAM1BMVEUAAABmZmZmZmZnZ2dnZ2dmZmZnZ2dmZmZsbGxnZ2dmZmZoaGhnZ2dmZmZnZ2dmZmZmZmYOy3DdAAAAEHRSTlMAVttEnOWJNwzzpVHsz8ZQ9cP58AAAAItJREFUKM+FkdsSgCAIRFXEa7f//9okSsqRaZ8WjrOoGJYtLmdXrPmquuOWq+9+OF4KQ39C6lX6mFL0l33SKB+BPSDNYW/pEPWZUMV3K5QjAymt9KQoIPas3FwSkFqZNbBoUZs23GvXBeWBq/YlQT5xkP0nsqg1dCKrXTYPzQkZxGTXCJoZ0YDZEeEEs9MNoPE7LN4AAAAASUVORK5CYII=) no-repeat;
        background-position: 50%;
        background-size: 12px;
        content: "";
        height: 100%;
        left: 10px;
        position: absolute;
        width: 12px;
      }
    }

    .xs-select-filter {
      border-color: #e5e5e5;
      border-style: solid;
      border-width: 0 0 1px;
      padding: 5px 10px;
      padding-left: 30px;
      width: 100%;
      -webkit-box-sizing: border-box;
      -moz-box-sizing: border-box;
      box-sizing: border-box;
      height: 30px;
      background-color: #fff;
      display: block;
      outline: none;
      text-overflow: ellipsis;
      overflow: hidden;
      white-space: nowrap;
    }

    .xs-select-option {
      background-color: #fff;
      display: block;
      outline: none;
      padding: 5px 10px;
      text-overflow: ellipsis;
      overflow: hidden;
      font-size: 12px;
      white-space: nowrap;

      &:hover, &.current {
        background-color: #38f;
        border-color: #38f;
        color: #fff;
      }
    }
  }

  .xs-select {
    cursor: pointer;
    display: inline-block;
    font-size: 0;
    line-height: 1.5;
    margin-right: 10px;
    outline: none;
    position: relative;
    text-align: left;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    width: 160px;
    vertical-align: middle;
    color: #666;

    .xs-select-text {
      @include box-sizing();
      border: 1px solid #dcdfe6;
      border-radius: 2px;
      display: inline-block;
      min-height: 30px;
      max-height: 76px;
      outline: none;
      padding: 5px 10px;

      font-size: 12px;
      @include transition(border-color, .25s);
      width: 100%;
      background-color: #fff;
      position: relative;
      &:after {
        background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAKCAMAAAC38k/IAAAAM1BMVEUAAAAzMzMzMzMzMzMzMzMzMzM0NDQ0NDQ1NTU1NTU9PT1VVVU0NDQzMzMzMzM1NTUzMzOoQ7gOAAAAEHRSTlMA99vkxpd9STUhCgPOzGhlqqcGJgAAAEBJREFUCNdFy8kRwCAMwEDZ3JDD/VebCXhgf3qIR83pi9gmXCduyokCyVwCuvrfAarMuzLlPzLLiGZx4FoIDeADc4gEoYNfTDsAAAAASUVORK5CYII=)  no-repeat;
        background-size: 6px 5px;
        content: "";
        height: 5px;
        margin-top: -3px;
        position: absolute;
        right: 10px;
        top: 50%;
        width: 6px;
      }
    }
  }


  .select-container {
    /*display: inline-block;*/
    zoom: 1;
    vertical-align: middle;

    &.select-dropdown-open {
      .select-choice {
        border: 1px solid #5897fb;
        background-image: linear-gradient(to bottom, #eee 0%, #fff 70%);
        border-bottom-color: transparent;
        -webkit-box-shadow: 0 1px 0 #fff inset;
        box-shadow: 0 1px 0 #fff inset;
        border-bottom-left-radius: 0;
        border-bottom-right-radius: 0;
        background-color: #eee;


        .select-arrow {
          border-left: none;
          background: transparent;

          b {
            background-position: -18px 1px;
          }
        }
      }

    }

    .select-result-label {
      background: #3875d7;
      color: #fff;
      font-size: 12px;
      padding: 6px;
      line-height: 1.2;
      cursor: pointer;

      i {
        font-size: 14px;
        font-weight: bold;
        margin-left: 5px;
      }
    }

    .select-choice {
      display: block;
      height: 26px;
      padding: 0 0 0 8px;
      overflow: hidden;
      position: relative;
      border: 1px solid #aaa;
      white-space: nowrap;
      line-height: 26px;
      color: #444;
      text-decoration: none;
      border-radius: 4px;
      background-clip: padding-box;
      background-color: #fff;

      padding-left: 5px;
      background-image: linear-gradient(to bottom, #fff 0%, #eee 50%);
      user-select: none;

      &:hover {
        background-image: linear-gradient(to bottom, #eee 0%, #fff 70%);
      }

      .select-arrow {
        display: inline-block;
        width: 18px;
        height: 100%;
        position: absolute;
        right: 0;
        top: 0;
        border-left: 1px solid #aaa;
        border-radius: 0 4px 4px 0;
        background-clip: padding-box;
        background: #ccc;
        background-image: linear-gradient(to bottom, #ccc 0%, #eee 60%);

        b {
          display: block;
          width: 100%;
          height: 100%;
          background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQAgMAAABinRfyAAAADFBMVEUAAACampqbm5uZmZn/DnGVAAAAA3RSTlMAnUqokJgHAAAAPklEQVQI12PQYACivQwMdQz7HRj/MchfYP/CwP5F/gID4996BwaG+r8MDAzyXyAEmAuWACsBKwZrAxsANgoAoL4XU6vB5h4AAAAASUVORK5CYII=) no-repeat;
        }
      }
    }

    .select-choices {
      height: auto;
      margin: 0;
      padding: 0;
      position: relative;
      border: 1px solid #bbb;

      padding: 2px 5px;
      cursor: text;
      overflow: hidden;
      background-color: #fff;

      @include flex($al: flex-start);
      flex-wrap: wrap;
      -webkit-flex-wrap: wrap;
      //align-items: flex-start;
      li {
      }

      .select-search-field {
        margin: 0;
        padding: 0;
        white-space: nowrap;
        flex:1;
        @include flex-a();
        min-width: 80px;

        input {
          width:100%;
          padding:  2px 5px;
          margin: 1px 0;
          font-family: sans-serif;
          color: #666;
          outline: 0;
          border: 0;
          -webkit-box-shadow: none;
          box-shadow: none;
          background: transparent !important;
          font-size: 12px;
          display: inline-block;
        }
      }

      .select-search-choice {
        background-color: #f8f8f8;
        border: 1px solid #bbb;
        border-radius: 2px;
        display: inline-block;
        margin: 2px 0;
        margin-right: 2px;
        padding: 0 4px;
        line-height: 18px;
        @include flex($al: center);
        font-size: 12px;

        .select-search-choice-close {
          font-size: 1px;
          outline: none;
          background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQAgMAAABinRfyAAAADFBMVEUAAACampqbm5uZmZn/DnGVAAAAA3RSTlMAnUqokJgHAAAAPklEQVQI12PQYACivQwMdQz7HRj/MchfYP/CwP5F/gID4996BwaG+r8MDAzyXyAEmAuWACsBKwZrAxsANgoAoL4XU6vB5h4AAAAASUVORK5CYII=) no-repeat;
          background-size: 8px;
          height: 8px;
          margin-left: 2px;
          width: 8px;
        }
      }

    }

  }

  .ui-popover {
    position: absolute;
    z-index: 1010;
    border-radius: 2px;
    -webkit-box-shadow: 0px 1px 6px rgba(0,0,0,0.2);
    box-shadow: 0px 1px 6px rgba(0,0,0,0.2);

    .ui-popover-inner {
      position: relative;
      background: #fff;
      border-radius: 2px;
      padding: 10px 20px;
      z-index: 2;

      box-sizing: content-box;

      .ui-btn {
        display: inline-block;
        border-radius: 2px;
        height: 26px;
        line-height: 26px;
        padding: 0 12px;
        cursor: pointer;
        color: #333;
        background: #f8f8f8;
        border: 1px solid #ddd;
        text-align: center;
        font-size: 12px;
        -webkit-box-sizing: content-box;
        -moz-box-sizing: content-box;
        box-sizing: content-box;
        margin-top: 10px;
        &.ui-btn-primary {
          color: #fff;
          background: #07d;
          border-color: #006cc9;
        }
      }
    }

    .arrow {
      position: absolute;
      width: 6px;
      height: 6px;
      background: #fff;
      -webkit-box-shadow: 0 1px 4px rgba(0,0,0,0.4);
      box-shadow: 0 1px 4px rgba(0,0,0,0.4);
      z-index: 1;
      top: 0;
      left: 50%;
      -webkit-transform: rotate(45deg) translateX(-50%) translateY(-50%);
      -moz-transform: rotate(45deg) translateX(-50%) translateY(-50%);
      -ms-transform: rotate(45deg) translateX(-50%) translateY(-50%);
      transform: rotate(45deg) translateX(-50%) translateY(-50%);
      -webkit-transform-origin: 0 0;
      -moz-transform-origin: 0 0;
      -ms-transform-origin: 0 0;
      transform-origin: 0 0;
    }

    .select-drop {
      position: relative;
      left: 0;
      top:0;
      width: 100%;
    }
  }

</style>
