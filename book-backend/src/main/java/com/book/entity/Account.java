package com.book.entity;

import lombok.Data;

@Data
public class User {
    Integer id;
    String username;
    String password;
    String role;
}
