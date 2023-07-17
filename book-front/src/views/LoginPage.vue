<template>
    <div class="login">
        <div class="loginHeader">
            <h3>登录</h3>
        </div>
        <div class="loginForm">
            <el-form :model="form">
                <div class="userNameInput">
                    <el-input v-model="form.username" placeholder="用户名或邮箱">
                        <template #prefix>
                            <el-icon>
                                <User/>
                            </el-icon>
                        </template>
                    </el-input>
                </div>
                <div class="pwdInput">
                    <el-input type="password" v-model="form.password" placeholder="密码">
                        <template #prefix>
                            <el-icon>
                                <Lock/>
                            </el-icon>
                        </template>
                    </el-input>
                </div>
                <el-row>
                    <el-col :span="12" style="text-align: left;">
                        <el-checkbox v-model="form.remember_me">记住我</el-checkbox>
                    </el-col>
                    <el-col :span="12" style="text-align: right">
                        <el-link>忘记密码?</el-link>
                    </el-col>
                </el-row>
                <div class="loginBtn">
                    <el-button style="width: 180px" @click="login()">登录</el-button>
                </div>
            </el-form>
            <div class="tips">
                <el-divider><span @click="router.push('/register')">没有账号?<el-link>立即注册</el-link></span></el-divider>
            </div>
        </div>
    </div>
</template>


<script setup>
import {reactive} from "vue";
import {User, Lock} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";
import router from "@/router/router";

const form = reactive({
    username: '',
    password: '',
    remember_me: false
})
const login = () => {
    if (!form.username || !form.password) {
        ElMessage.warning("请填写用户名和密码")
    } else {
        post("/api/auth/login", {
            username: form.username,
            password: form.password,
            remember_me: form.remember_me
        }, (msg) => {
            ElMessage.success(msg)
            router.push('/index')

        })
    }
}
</script>

<style>

.login {
    width: 500px;
    background-color: white;
    text-align: center;
    font-size: 30px;
}

.loginForm {
    margin: 0 100px;
}

.loginHeader {
    margin-top: 140px;
}

.pwdInput {
    margin-top: 10px;
}

.loginBtn {
    margin-top: 30px;
}

.tips {
    margin-top: 50px;
}
</style>
