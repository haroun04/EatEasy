package com.EatEasy.Services;

import com.EatEasy.Models.User;
import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User save(User user);

    void deleteById(Long id);

    User update(Long id, User user);
}
