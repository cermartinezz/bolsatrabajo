package com.bolsaTrabajo.service;


import com.bolsaTrabajo.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
