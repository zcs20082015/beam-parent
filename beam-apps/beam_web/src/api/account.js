
import http from '@/utils/http'


export default {
    // 修改密码
    modifyPwd : params => {
        return http.post("/system/user/change/password", params)
    },
    handleLogout:()=>{
    	return http.get("/logout", {})
    }
}
