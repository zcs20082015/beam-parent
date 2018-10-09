package com.hsshy.beam.admin.modular.system.controller;
import com.google.code.kaptcha.Constants;
import com.hsshy.beam.admin.core.shiro.ShiroUtils;
import com.hsshy.beam.admin.core.util.KaptchaUtil;
import com.hsshy.beam.admin.modular.system.entity.User;
import com.hsshy.beam.common.support.HttpKit;
import com.hsshy.beam.common.utils.R;
import com.hsshy.beam.web.base.controller.beam.BaseAdminController;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController extends BaseAdminController<User,Long> {



    /**
     * 跳转到主页
     */
    @GetMapping(value = "/")
    public String index(Model model) {
        //获取菜单列表


        System.out.println("account:"+ShiroUtils.getUserEntity().getAvatar());

        model.addAttribute("shiroUser",ShiroUtils.getUserEntity());


        return "/index.html";
    }

    @GetMapping(value = "/login")
    public String login(){


        return "/login.html";
    }

    @PostMapping(value = "/login/form")
    @ResponseBody
    public Object loginForm(String username, String password, String captcha){

        if(new KaptchaUtil().isKaptchaOnOff()){
            String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
            if(!captcha.equalsIgnoreCase(kaptcha)){
                return R.fail("验证码不正确");
            }
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
        return R.ok("登陆成功");
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
        return "redirect:/login";
    }



}
