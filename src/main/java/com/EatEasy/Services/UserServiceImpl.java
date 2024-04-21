package com.EatEasy.Services;

import com.EatEasy.Models.User;
import com.EatEasy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(Long id, User model) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(model.getName());
            user.setEmail(model.getEmail());
            user.setPassword(model.getPassword());
            user.setProfilePicture(model.getProfilePicture());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User findBynameAndPassword(String name, String password) {
        return userRepository.findBynameAndPassword(name, password);
    }
}