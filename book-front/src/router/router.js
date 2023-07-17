import {createRouter, createWebHashHistory} from "vue-router";
const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            name: 'main',
            component: import("@/components/MainPage.vue"),
            children: [
                {
                    path: '',
                    name: 'login',
                    component: import('@/views/LoginPage.vue')
                },
                {
                    path: 'register',
                    name: 'register',
                    component: import('@/views/RegisterPage.vue')
                }
            ]
        },
        {
            path: '/index',
            name: 'index',
            component: import("@/views/IndexPage.vue")
        }
    ]
})

export default router;