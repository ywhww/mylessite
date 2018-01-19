package com.jc.mylessite.modules.sys.security;

import com.google.code.kaptcha.Constants;
import com.jc.mylessite.modules.sys.entity.SysUser;
import com.jc.mylessite.modules.sys.security.utils.Principal;
import com.jc.mylessite.modules.sys.services.UserService;
import com.jc.mylessite.modules.sys.utils.UserUtils;
import com.jc.mylessite.modules.sys.web.LoginController;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        if (LoginController.isValidateCodeLogin(username,false,false)) {
            // 验证码验证
            String volidateCode = usernamePasswordToken.getVolidateCode();
            String realVolidateCode = (String) UserUtils.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

            if (volidateCode==null||!volidateCode.equals(realVolidateCode)){
                throw new AuthenticationException("msg:验证码错误，请重试");
            }
        }

        SysUser sysUser = userService.findUserByName(username);
        if (sysUser!=null){
            String id = sysUser.getId();
            String name = sysUser.getName();
            String password = sysUser.getPassword();
            return new SimpleAuthenticationInfo(new Principal(id,name,password),password,getName());
        }
        return null;
    }
}
