
import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import getters from './getters'
import editor from './modules/vue-html5-editor'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        user,
        editor,
    },
    getters
})

export default store
