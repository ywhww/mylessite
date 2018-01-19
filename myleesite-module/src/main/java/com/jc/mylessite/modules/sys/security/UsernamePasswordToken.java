package com.jc.mylessite.modules.sys.security;



public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    public UsernamePasswordToken(String username, String password, String host) {
        super(username, password, host);
    }
}
