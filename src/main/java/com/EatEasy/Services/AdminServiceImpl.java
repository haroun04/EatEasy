package com.EatEasy.Services;

import com.EatEasy.Models.Admin;
import com.EatEasy.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin findById(Long id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        return adminOptional.orElse(null);
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Admin update(Long id, Admin admin) {
        if (adminRepository.existsById(id)) {
            admin.setId(id); // Asigna el ID al administrador para asegurarse de que se actualice el administrador correcto
            return adminRepository.save(admin);
        }
        return null; // O puedes lanzar una excepción o realizar otras acciones en caso de que el ID no exista
    }

    @Override
    public Admin findByName(String name) {
        Optional<Admin> adminOptional = adminRepository.findByName(name);
        return adminOptional.orElse(null);
    }
}
