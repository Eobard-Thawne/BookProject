import axios from "axios";
import {ElMessage} from "element-plus";

const defaultError = () => ElMessage.error("发生了一些错误")
const defaultFailure = (msg) => ElMessage.warning(msg)

const BASE_URL = "http://localhost:8080"

function post(url, data, success, failure = defaultFailure, error = defaultError) {
    axios.post(BASE_URL + url, data, {
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        withCredentials: true
    }).then(({data}) => {
        if (data.success) {
            success(data.msg, data.status)
        } else {
            failure(data.msg, data.status)
        }
    }).catch(error)
}

function get(url, success, failure = defaultFailure, error = defaultError) {
    axios.get(BASE_URL + url, {
        withCredentials: true
    }).then(({data}) => {
        if (data.success) {
            success(data.msg, data.status)
        } else {
            failure(data.msg, data.status)
        }
    }).catch(error)
}

export {get, post}