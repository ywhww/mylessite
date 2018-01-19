package com.jc.mylessite.modules.sys.web;

import com.jc.mylessite.common.utils.CacheUtils;
import com.jc.mylessite.modules.sys.security.utils.Principal;
import com.jc.mylessite.modules.sys.utils.UserUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
public class LoginController {

    /**
     * 登陆页面
     * @return
     */
   @RequestMapping(value = "login")
    public String login(){
       Principal principal = UserUtils.getPrincipal();
       if (principal!=null){
           return "modules/sys/main";
       }
       return "modules/sys/login";

   }

    /**
     * 登陆失败后 shiro 跳转的方法
     * @param request
     * @param model
     * @return
     */
   @RequestMapping(value = "login",method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request, Model model){
       String message = (String) request.getAttribute("message");
       String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
       if (message!=null){
           model.addAttribute("isValidateCodeLogin",isValidateCodeLogin(username,true,false));
           model.addAttribute("message",message);
           return "modules/sys/login";
       }

       return "modules/sys/main";
   }


    /**
     * 登陆成功后的管理
     * @param request
     * @return
     */
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String main(HttpServletRequest request){
        Principal principal = UserUtils.getPrincipal();
        String name = principal.getName();
        // 清楚登陆错误次数
        LoginController.isValidateCodeLogin(name,false,true);
        return "modules/sys/main";
    }

    /**
     * 判断是否验证码登陆
     * @param username
     * @param isfail
     * @param clean
     * @return
     */
   public static boolean isValidateCodeLogin(String username,boolean isfail,boolean clean){
       HashMap<String,Integer> failmap = (HashMap<String, Integer>) CacheUtils.get("failmap");
       if (failmap==null){
           failmap = new HashMap<>();
           CacheUtils.put("failmap",failmap);
       }
       Integer failnum = failmap.get(username);
       if (failnum==null){
           failnum = 0;
           failmap.put(username,failnum);
       }
       // 失败登陆次数加1
       if (isfail){
           failnum++;
           failmap.put(username,failnum);
       }
       // 清楚登陆失败次数
       if (clean){
           CacheUtils.remove(username);
       }
       return failnum>=3;
   }

}
