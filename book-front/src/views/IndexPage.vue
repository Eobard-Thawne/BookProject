<template>
  <h1>这是主页</h1>
  <el-button @click="logout()">退出登录</el-button>
</template>

<script setup>
import {get} from "@/net";
import {ElMessage} from "element-plus";
function logout() {
    get("/api/auth/logout", (msg)=>{
        ElMessage.success(msg)
    })
}
</script>

<style>
h1 {
    text-align: center;
}
</style>