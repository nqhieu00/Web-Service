package com.banlaptop.shop.repository;

import com.banlaptop.shop.entity.User;

public interface UserRepository extends GenericRepository<User,Long> {

    User findByEmail(String email);

}
