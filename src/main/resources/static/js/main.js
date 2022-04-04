import Vue from 'vue'
import 'api/resource'
import router from './router/router'
import App from 'pages/App.vue'
import {connect} from './util/ws'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import store from './store/store'
import 'vuetify/dist/vuetify.min.css'

if (profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    router,
    vuetify: new Vuetify(),
    render: a => a(App)
})