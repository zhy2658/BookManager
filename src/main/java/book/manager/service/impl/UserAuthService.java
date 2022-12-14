package book.manager.service.impl;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAuthService implements UserDetailsService {

    @Resource
    UserMapper mapper;

    //验证登录
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser authUser = mapper.getPasswordByUserName(s);  //用mapper查询数据库user表
        if(authUser == null) throw new UsernameNotFoundException("Login failed.Uname or pwd is error");
        return User.withUsername(s)
                .password(authUser.getPassword())
                .roles(authUser.getRole())
//                .authorities("page:index","page:admin")
                .build();
    }
}
