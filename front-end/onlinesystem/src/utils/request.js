import { Message, Loading } from 'element-ui';
import router from '../router'
const axios = require('axios')
const env = process.env.NODE_ENV

const ajax = function (url, params, methods, isFormType = true, openLoading = true, loadingMsg = '加载中...') {
    if (arguments.length === 1 && typeof arguments[0] === "object") {
        ({
        url,
        params,
        methods,
        isFormType = true,
        openLoading = true,
        loadingMsg = '加载中...'
        } = arguments[0]);
    }
    if (!url) {
        throw new Error("params 'url' must be need and cannot empty!")
    } else if (typeof params === 'undefined') {
        throw new Error("params 'params' must be need!")
    }
    if (!methods) {
        if (typeof url === "object" && url.methods) {
        methods = url.methods;
        } else {
        methods = 'post';
        }
    }
    if (typeof url === "object") {
        url = url.api;
    }
    console.log( process.env.VUE_APP_URL )
    let token = window.localStorage.getItem('token'),
        baseURL = process.env.VUE_APP_URL,
        instance = null,
        opts = null,
        loading = null,
        loadingOpts = {
            lock: true,
            text: loadingMsg,
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
        }
    instance = axios.create({
        baseURL: baseURL,
        timeout: 20000,
        headers: {
            // 'Authorization': token? token: "",
            'content-type': methods == "post" ? 'application/json;charset=UTF-8' : 'application/x-www-form-urlencoded;charset=UTF-8',
        }
    });
    instance.interceptors.request.use(function (config) {
        if ( openLoading ) {
            loading = Loading.service(loadingOpts);
        }
        return config;
    }, function (error) {
        openLoading && loading.close();
        return Promise.reject(error);
    });

    instance.interceptors.response.use(function (response) {
        openLoading && loading.close();
        return response;
    }, function (error) {
        if( error.code == "ECONNABORTED" && error.message.indexOf("timeout") !== -1){
            error.message = "请求超时，请稍后再试";
        }
        Message.error({
            message: error.message
        })
        openLoading && loading.close();
        return Promise.reject(error);
    });

    opts = {
        method: methods,
        url: url,
    }
    if (methods === "get") {
        opts['params'] = params;
    } else if (methods === "post") {
        opts['data'] = isFormType ? params : JSON.stringify(params);
    }
    return instance(opts).then((res) => {
        if (env == 'development') {
            console.log(`%c 接口请求开始`, 'background: #008000; color: #fff')
            console.log('接口地址 %s', baseURL + url)
            console.log('接口返回 %O', res.data)
            console.log('参数 %O', opts)
            console.log(`%c 接口请求结束`, 'background: #ff0000; color: #fff')
        }
        let code = parseInt(res.data.code);
        if (code === 403) {
            router.push('/login');
            return res.data;
        } else {
            return res.data;
        }
    })
}
export default ajax