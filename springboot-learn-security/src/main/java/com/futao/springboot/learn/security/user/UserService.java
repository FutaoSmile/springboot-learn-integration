package com.futao.springboot.learn.security.user;

import com.futao.springboot.learn.security.dao.RoleDao;
import com.futao.springboot.learn.security.dao.UserDao;
import com.futao.springboot.learn.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author futao
 * Created on 2019-07-23.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setRoleList(roleDao.rolesByUserId(user.getId()));
        return user;
    }
}
