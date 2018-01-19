package com.jc.mylessite.modules.sys.web;

import com.jc.mylessite.common.utils.CacheUtils;
import com.jc.mylessite.modules.sys.security.utils.Principal;
import com.jc.mylessite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
public class LoginController {

   @RequestMapping(value = "login")
    public String login(){
       Principal principal = UserUtils.getPrincipal();
       if (principal!=null){
           return "modules/sys/main";
       }
       return "modules/sys/login";

   }

   @RequestMapping(value = "login",method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request){

        return "modules/sys/login";
   }

   private static boolean isValidateCodeLogin(String username,boolean isfail,boolean clean){
       HashMap<String,Integer> failmap = (HashMap<String, Integer>) CacheUtils.get("failmap");
       if (failmap==null){
           failmap = new HashMap<>();
           CacheUtils.put(username,failmap);
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
