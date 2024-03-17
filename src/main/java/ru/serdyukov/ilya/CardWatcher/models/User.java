package ru.serdyukov.ilya.CardWatcher.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    public User() {
    }

}
