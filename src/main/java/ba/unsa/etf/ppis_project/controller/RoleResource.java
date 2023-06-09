package ba.unsa.etf.ppis_project.controller;

import ba.unsa.etf.ppis_project.dto.RoleDTO;
import ba.unsa.etf.ppis_project.service.RoleService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleResource {

    private final RoleService roleService;

    public RoleResource(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable(name = "roleId") final Integer roleId) {
        return ResponseEntity.ok(roleService.get(roleId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createRole(@RequestBody @Valid final RoleDTO roleDTO) {
        final Integer createdRoleId = roleService.create(roleDTO);
        return new ResponseEntity<>(createdRoleId, HttpStatus.CREATED);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Void> updateRole(@PathVariable(name = "roleId") final Integer roleId,
            @RequestBody @Valid final RoleDTO roleDTO) {
        roleService.update(roleId, roleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{roleId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteRole(@PathVariable(name = "roleId") final Integer roleId) {
        roleService.delete(roleId);
        return ResponseEntity.noContent().build();
    }

}
