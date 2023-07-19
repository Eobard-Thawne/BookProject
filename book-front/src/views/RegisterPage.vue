<template>
    <div class="reg">
        <div class="regHeader">
            <h3>注册</h3>
        </div>
        <el-form :model="form" class="regForm" :rules="rules">
            <el-form-item prop="username">
                <el-input v-model="form.username" placeholder="用户名">
                    <template #prefix>
                        <el-icon>
                            <User/>
                        </el-icon>
                    </template>
                </el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="form.password" placeholder="输入密码">
                    <template #prefix>
                        <el-icon>
                            <Lock/>
                        </el-icon>
                    </template>
                </el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="form.repeat_pwd" placeholder="重新输入密码">
                    <template #prefix>
                        <el-icon>
                            <Lock/>
                        </el-icon>
                    </template>
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-input type="text" v-model="form.email" placeholder="电子邮件地址">
                    <template #prefix>
                        <el-icon>
                            <Message/>
                        </el-icon>
                    </template>
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-row>
                    <el-col :span="16">
                        <el-input type="text" v-model="form.code" placeholder="验证码">
                            <template #prefix>
                                <el-icon>
                                    <Key/>
                                </el-icon>
                            </template>
                        </el-input>
                    </el-col>
                    <el-col :span="1">
                        <el-button>获取验证码</el-button>
                    </el-col>
                </el-row>
            </el-form-item>

            <div class="regBtn">
                <el-button style="width: 180px">立即注册</el-button>
            </div>
            <div class="tips">
                <el-divider>已有账号?
                    <el-link><span @click="router.push('/')">立即登录</span></el-link>
                </el-divider>
            </div>
        </el-form>
    </div>
</template>

<script setup>
import {reactive} from "vue";
import {User, Lock, Message, Key} from "@element-plus/icons-vue";
import router from "@/router/router";


const form = reactive({
    username: '',
    password: '',
    repeat_pwd: '',
    email: '',
    code: ''
})

const validateUserName = (rule, value, callback) => {
    const pattern = /^[A-Za-z][A-Za-z0-9]*$/;
    const re = new RegExp(pattern);
    if (value === '') {
        callback(new Error('请输入用户名'))
    } else if (!re.test(value)) {
        callback(new Error("不能包含特殊字符且只能以字母开头"))
    } else {
        callback()
    }
}
const validateRepeatPassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入密码'))
    } else {
        callback()
    }
}

const rules = {
    username: [
        {validator: validateUserName, trigger: ['blur', 'change']},
        {min: 3, max: 8, message: '用户名长度为3-8个字符', trigger: ['blur', 'change']},
    ],
    password: [
        {min: 4, max: 8, message: '用户名长度为4-8个字符,不能包含特殊字符', trigger: ['blur', 'change']}
    ],
    repeatPwd: [
        // {min: 4, max: 8, message: '用户名长度为4-8个字符,不能包含特殊字符', trigger: ['blur', 'change']},

        {validator: validateRepeatPassword, trigger: ['blur', 'change']}
        // {min: 4, max: 8, message: '用户名长度为4-8个字符,不能包含特殊字符', trigger: ['blur', 'change']}

    ]
}
</script>

<style>
.reg {
    width: 500px;
    background-color: white;
    text-align: center;
    font-size: 30px;
}

.regForm {
    margin: 0 100px;
}

.regHeader {
    margin-top: 140px;
}

.regBtn {
    margin-top: 30px;
}

.tips {
    margin-top: 50px;
}
</style>