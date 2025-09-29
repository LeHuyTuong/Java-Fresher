package com.example.demohsfs3.service;

import com.example.demohsfs3.entity.User;

import java.util.List;

public interface UserService {
    public boolean createUser(User user);
    public void updateUser(int id, User user);
    public void deleteUser(int id);
    public List<User> getAllUsers();
    public User getUserById(int id);
    public User getUser(String username, String password);
}
