package com.jc.mylessite.modules.sys.security;


import com.jc.mylessite.common.utils.CacheUtils;
import com.jc.mylessite.common.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String remoteAddr = StringUtils.getRemoteAddr((HttpServletRequest) request);
        return new UsernamePasswordToken(username,password,remoteAddr);
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        super.issueSuccessRedirect(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String name = e.getClass().getName(),message = "";
        if (IncorrectCredentialsException.class.getName().equals(name)|| UnknownAccountException.class.getName().equals(name)){
            message = "用户名或者密码错误，请重试";
        }
        else if (e.getMessage()!=null&&StringUtils.startsWith(e.getMessage(),"msg")){
            message = StringUtils.replace(e.getMessage(),"msg","");
        }
        else {
            message = "系统出现异常，请稍后登陆";
            e.printStackTrace();
        }

        System.out.println(name);

        request.setAttribute("message",message);
        request.setAttribute("shiroLoginFailure",name);
        return super.onLoginFailure(token, e, request, response);
    }
}
