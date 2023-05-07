package ba.unsa.etf.ppis_project.role;

import ba.unsa.etf.ppis_project.doctor.DoctorRepository;
import ba.unsa.etf.ppis_project.patient.PatientRepository;
import ba.unsa.etf.ppis_project.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public RoleService(final RoleRepository roleRepository,
            final PatientRepository patientRepository, final DoctorRepository doctorRepository) {
        this.roleRepository = roleRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<RoleDTO> findAll() {
        final List<Role> roles = roleRepository.findAll(Sort.by("roleId"));
        return roles.stream()
                .map((role) -> mapToDTO(role, new RoleDTO()))
                .toList();
    }

    public RoleDTO get(final Integer roleId) {
        return roleRepository.findById(roleId)
                .map(role -> mapToDTO(role, new RoleDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RoleDTO roleDTO) {
        final Role role = new Role();
        mapToEntity(roleDTO, role);
        return roleRepository.save(role).getId();
    }

    public void update(final Integer roleId, final RoleDTO roleDTO) {
        final Role role = roleRepository.findById(roleId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(roleDTO, role);
        roleRepository.save(role);
    }

    public void delete(final Integer roleId) {
        roleRepository.deleteById(roleId);
    }

    private RoleDTO mapToDTO(final Role role, final RoleDTO roleDTO) {
        roleDTO.setRoleId(role.getId());
        roleDTO.setRoleName(role.getRoleName());
//        roleDTO.setRole(role.getRole() == null ? null : role.getRole().getId());
//        roleDTO.setRola(role.getRola() == null ? null : role.getRola().getId());
        return roleDTO;
    }

    private Role mapToEntity(final RoleDTO roleDTO, final Role role) {
        role.setRoleName(roleDTO.getRoleName());
//        final Patient role = roleDTO.getRole() == null ? null : patientRepository.findById(roleDTO.getRole())
//                .orElseThrow(() -> new NotFoundException("role not found"));
//        role.setRole(role);
//        final Doctor rola = roleDTO.getRola() == null ? null : doctorRepository.findById(roleDTO.getRola())
//                .orElseThrow(() -> new NotFoundException("rola not found"));
//        role.setRola(rola);
        return role;
    }

}
