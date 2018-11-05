
const state = {
  isShowUpload: false
}

const getters = {
  isShowUpload: state => state.isShowUpload
}

const mutations = {
  showUpload(state, val) {
    state.isShowUpload = val
  }

}

const actions = {

}

export default {
  state,
  getters,
  actions,
  mutations
}
