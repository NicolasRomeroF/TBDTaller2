import Vue from 'vue';
import VueRouter from 'vue-router';
import Index from './Index.vue';
import Actors from './Actors.vue';
import VueResource from 'vue-resource';
import ActorNuevo from './Actornuevo.vue';
import Games from './games.vue';
import Filtraciones from './filtraciones.vue';
require("./style.scss");

import App from './App.vue';
Vue.use(VueRouter);
Vue.use(VueResource);
const routes = [
  { path: '/index', alias: '/', component: Index},
  { path: '/actors', component: Actors},
  { path: '/actors/new',component: ActorNuevo},
  { path: '/games', component: Games},
  { path: '/filter_game', component: Filtraciones}
]

// Create the router instance and pass the `routes` option
const router = new VueRouter({
  routes
})

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
