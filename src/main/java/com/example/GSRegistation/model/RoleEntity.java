package com.example.GSRegistation.model;

import javax.persistence.*;

@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "role")
    private UserEntitiy user;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntitiy getUser() {
        return user;
    }

    public void setUser(UserEntitiy user) {
        this.user = user;
    }
}
