package com.first.first.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_role")
@Getter
@Setter
public class UserRole {

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "uer_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

}
