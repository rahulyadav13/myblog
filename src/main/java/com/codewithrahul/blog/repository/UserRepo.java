package com.codewithrahul.blog.repository;

import com.codewithrahul.blog.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {

}
