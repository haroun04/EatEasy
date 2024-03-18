package com.EatEasy.Services;

import com.EatEasy.Models.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> findAll();

    Admin findById(Long id);

    Admin save(Admin admin);

    void deleteById(Long id);

    Admin update(Long id, Admin admin);

    Admin findByName(String name);
}
