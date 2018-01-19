package com.jc.mylessite.modules.sys.security;



public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    private String volidateCode;

    public UsernamePasswordToken(String username, String password, String host,String volidateCode) {
        super(username, password, host);
        this.volidateCode = volidateCode;
    }

    public String getVolidateCode() {
        return volidateCode;
    }

    public void setVolidateCode(String volidateCode) {
        this.volidateCode = volidateCode;
    }
}
