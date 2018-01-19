package com.jc.mylessite.modules.sys.utils;

import com.jc.mylessite.modules.sys.security.utils.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class UserUtils {

    public static Principal getPrincipal(){
        Subject subject = SecurityUtils.getSubject();
        Principal principal = (Principal) subject.getPrincipal();
        if (principal!=null){
            return principal;
        }
        return null;
    }

    public static Session getSession(){
        Subject subject = SecurityUtils.getSubject();
        // false 不创建新会话
        Session session = subject.getSession(false);
        if (session==null){
            session = subject.getSession();
        }
        return session;
    }
}
