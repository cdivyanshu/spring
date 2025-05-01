package com.svn.app.service;

import com.svn.app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List getAllUsers();
    void saveUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
}
