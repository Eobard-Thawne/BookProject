<script>
</script>
<template>
    <div class="container">
        <div style="flex: 1">
            <el-image class="bg" :src="bg" fit="cover"/>
        </div>
        <div class="login">
            <transition name="el-zoom-in-center">
                <router-view/>
            </transition>
        </div>
    </div>
</template>

<script setup>
import bg from "@/assets/bg.jpg";
</script>

<style>
.container {
    overflow: hidden;
    display: flex;
    width: 100vw;
    height: 100vh
}

.bg {
    width: 100%;
    height: 100%
}

.login {
    width: 500px;
    background-color: white;
    text-align: center;
    font-size: 30px;
}

.loginForm {
    margin: 0 100px;
}
</style>
