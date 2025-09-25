package com.team_yerng.l_s_m_s.controllers;

import com.team_yerng.l_s_m_s.dto.RoleDto;
import com.team_yerng.l_s_m_s.service.RoleService;
import com.team_yerng.l_s_m_s.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<RoleDto>> createRole(@RequestBody RoleDto dto) {
        return ResponseEntity.ok(
                ApiResponse.<RoleDto>builder()
                        .success(true)
                        .code(200)
                        .message("Role created successfully")
                        .data(roleService.createRole(dto))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDto>>> getAllRoles() {
        return ResponseEntity.ok(
                ApiResponse.<List<RoleDto>>builder()
                        .success(true)
                        .code(200)
                        .message("Roles retrieved successfully")
                        .data(roleService.getAllRoles())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDto>> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<RoleDto>builder()
                        .success(true)
                        .code(200)
                        .message("Role retrieved successfully")
                        .data(roleService.getRoleById(id))
                        .build()
        );
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDto>> updateRole(@PathVariable Long id, @RequestBody RoleDto dto) {
        return ResponseEntity.ok(
                ApiResponse.<RoleDto>builder()
                        .success(true)
                        .code(200)
                        .message("Role updated successfully")
                        .data(roleService.updateRole(id, dto))
                        .build()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
