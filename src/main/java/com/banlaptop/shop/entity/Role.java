package com.banlaptop.shop.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role extends Base {

    private String name;

    @OneToMany(mappedBy = "role")
    List<UserRole> userRoles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
