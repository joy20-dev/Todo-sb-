package com.JoyBoy.ToDo.Models;


import java.lang.annotation.Inherited;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Table(name="Users")
@Data
public class User{
    @Id 
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique=true)
    private String userName;
    private String password;
    private String role ="ROLE_USER";
}