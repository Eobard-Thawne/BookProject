import {createApp} from 'vue'
import App from './App.vue'
import router from "@/router/router";
import "element-plus/dist/index.css"
import ElementPlus from "element-plus"
import * as ElementIcons from '@element-plus/icons-vue'

const app = createApp(App)
app.use(router)
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementIcons)) {
    app.component(key, component)
}
app.mount('#app')
