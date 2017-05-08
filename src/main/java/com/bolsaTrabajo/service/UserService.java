package com.bolsaTrabajo.service;


import com.bolsaTrabajo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> getAll();
}
