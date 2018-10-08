package com.hsshy.beam.admin.modular.system.controller;
import com.google.code.kaptcha.Constants;
import com.hsshy.beam.admin.core.shiro.ShiroUtils;
import com.hsshy.beam.admin.modular.system.entity.User;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.web.base.controller.beam.BaseAdminController;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController extends BaseAdminController<User,Long> {



    /**
     * 跳转到主页
     */
    @GetMapping(value = "/")
    public String index() {
        //获取菜单列表


        //获取用户头像


        return "/index.html";
    }

    @GetMapping(value = "/login")
    public String login(){


        return "/login.html";
    }

    @PostMapping(value = "/login/form")
    public Object loginForm(String username, String password, String captcha){

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!captcha.equalsIgnoreCase(kaptcha)){
            return R.fail("验证码不正确");
        }

        try{
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return R.fail(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return R.fail("账号或密码不正确");
        }catch (LockedAccountException e) {
            return R.fail("账号已被锁定,请联系管理员");
        }catch (AuthenticationException e) {
            return R.fail("账户验证失败");
        }
        return REDIRECT + "/";
    }

    @GetMapping(value = "/login/error")
    public String loginError(){


        return "/404.html";
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login.html";
    }



}
