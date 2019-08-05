package com.futao.springboot.learn.security.dao;

import com.futao.springboot.learn.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author futao
 * Created on 2019-07-23.
 */
public interface RoleDao extends JpaRepository<Role, Integer> {

    @Query("select sr from security_role sr,security_user_role sur where sr.id=sur.role_id and sur.user_id=:userId")
    List<Role> rolesByUserId(@Param("userId") int userId);
}
