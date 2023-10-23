package com.kramti.repository;

import com.kramti.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Long> {
    public User findByUsername(String username){
        return find("username", username).firstResult();
    }
}
