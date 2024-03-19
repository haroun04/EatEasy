package com.EatEasy.Controllers;

import com.EatEasy.Dtos.AdminRequestDto;
import com.EatEasy.Dtos.AdminResponseDto;
import com.EatEasy.Mapper.AdminMapper;
import com.EatEasy.Services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@Slf4j
public class AdminController {
    private final AdminMapper adminMapper;
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminMapper adminMapper, AdminService adminService) {
        this.adminMapper = adminMapper;
        this.adminService = adminService;
    }

    @GetMapping("")
    public ResponseEntity<List<AdminResponseDto>> getAllAdmins(){
        log.info("getAllAdmins");
        return ResponseEntity.ok(
                adminMapper.toResponse(adminService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDto> getAdminByID(@PathVariable Long id){
        log.info("getAdminByID");
        return ResponseEntity.ok(
                adminMapper.toResponse(adminService.findById(id))
        );
    }

    @PostMapping("")
    public ResponseEntity<AdminResponseDto> createAdmin(@RequestBody AdminRequestDto adminRequestDto){
        log.info("createAdmin");
        AdminResponseDto adminResponseDto = adminMapper.toResponse(adminService.save(adminMapper.toModel(adminRequestDto)));
        return ResponseEntity.ok(adminResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponseDto> updateAdmin(@PathVariable Long id, @RequestBody AdminRequestDto adminRequestDto){
        log.info("updateAdmin");
        AdminResponseDto adminResponseDto = adminMapper.toResponse(adminService.update(id, adminMapper.toModel(adminRequestDto)));
        return ResponseEntity.ok(adminResponseDto);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id){
        log.info("deleteAdmin");
        adminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
