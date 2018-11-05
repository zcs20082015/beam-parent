
import http from '@/utils/http'


export default {
    // 登录
    login : params => {
        return http.post("/login", params)
    },
}
