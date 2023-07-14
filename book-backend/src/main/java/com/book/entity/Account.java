package com.book.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Account {
    Integer id;
    String username;
    String password;
    String role;
    Date createDate;
}
