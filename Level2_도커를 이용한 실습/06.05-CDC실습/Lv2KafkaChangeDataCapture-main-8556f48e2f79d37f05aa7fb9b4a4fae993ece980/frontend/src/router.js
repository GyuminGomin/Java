
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderManager from "./components/listers/OrderCards"
import OrderDetail from "./components/listers/OrderDetail"

import SyncOrderManager from "./components/listers/SyncOrderCards"
import SyncOrderDetail from "./components/listers/SyncOrderDetail"
import SuggestionManager from "./components/listers/SuggestionCards"
import SuggestionDetail from "./components/listers/SuggestionDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orders',
                name: 'OrderManager',
                component: OrderManager
            },
            {
                path: '/orders/:id',
                name: 'OrderDetail',
                component: OrderDetail
            },

            {
                path: '/syncOrders',
                name: 'SyncOrderManager',
                component: SyncOrderManager
            },
            {
                path: '/syncOrders/:id',
                name: 'SyncOrderDetail',
                component: SyncOrderDetail
            },
            {
                path: '/suggestions',
                name: 'SuggestionManager',
                component: SuggestionManager
            },
            {
                path: '/suggestions/:id',
                name: 'SuggestionDetail',
                component: SuggestionDetail
            },



    ]
})
