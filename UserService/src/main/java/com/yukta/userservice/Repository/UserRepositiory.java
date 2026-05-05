package com.yukta.userservice.Repository;

import com.yukta.userservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositiory extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
