package com.shann.springredis.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table(name = "USER_ENTITY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

}
