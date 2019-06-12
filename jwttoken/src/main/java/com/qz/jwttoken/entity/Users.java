package com.qz.jwttoken.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Users {
    private String account;
    private String password;
    private String username;
    private String phone;
}
