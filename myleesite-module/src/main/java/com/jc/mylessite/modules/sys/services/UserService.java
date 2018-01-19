package com.jc.mylessite.modules.sys.services;

import com.jc.mylessite.modules.sys.dao.SysUserMapper;
import com.jc.mylessite.modules.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private SysUserMapper userMapper;

    public SysUser login(SysUser sysUser){
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("",sysUser.getLoginName())
                .andEqualTo("",sysUser.getPassword());
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        if (sysUser!=null&&sysUsers.size()==1){
            return sysUsers.get(0);
        }
        return null;
    }

    public SysUser findUserByName(String username){
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("name",username);
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        if (sysUsers!=null&&sysUsers.size()==1){
            return sysUsers.get(0);
        }
        return null;
    }

}
