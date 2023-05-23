package ba.unsa.etf.ppis_project.service;

import ba.unsa.etf.ppis_project.dto.DoctorDTO;
import ba.unsa.etf.ppis_project.model.Doctor;
import ba.unsa.etf.ppis_project.repos.DoctorRepository;
import ba.unsa.etf.ppis_project.repos.ExaminationRepository;
import ba.unsa.etf.ppis_project.model.Role;
import ba.unsa.etf.ppis_project.model.User;
import ba.unsa.etf.ppis_project.repos.ServiceRepository;
import ba.unsa.etf.ppis_project.repos.UserRepository;
import ba.unsa.etf.ppis_project.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ExaminationRepository examinationRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;


    public DoctorService(final DoctorRepository doctorRepository,
            final ExaminationRepository examinationRepository,
                         final ServiceRepository serviceRepository,
                         final UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.examinationRepository = examinationRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    public List<DoctorDTO> findAll() {
        final List<Doctor> doctors = doctorRepository.findAll(Sort.by("id"));
        return doctors.stream()
                .map((doctor) -> mapToDTO2(doctor, new DoctorDTO()))
                .toList();
    }

    private DoctorDTO mapToDTO2(Doctor doctor, DoctorDTO doctorDTO) {
        doctorDTO.setDoctorId(doctor.getId());
        doctorDTO.setUsername(doctor.getUsername());
        doctorDTO.setPassword(doctor.getPassword());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setSurname(doctor.getSurname());
        doctorDTO.setDateOfBrith(doctor.getDateOfBrith());
        doctorDTO.setRoleId(2);
        doctorDTO.setFieldOfExpertise(doctor.getFieldOfExpertise());
        return doctorDTO;
    }

    public DoctorDTO get(final Integer doctorId) {
        return doctorRepository.findById(doctorId)
                .map(doctor -> mapToDTO(doctor, new DoctorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final DoctorDTO doctorDTO) {
        final Doctor doctor = new Doctor();
        mapToEntity2(doctor, doctorDTO);
        User user = new User(doctor.getUsername(), doctor.getPassword(), "doctor");
        userRepository.save(user);
        return doctorRepository.save(doctor).getId();
    }

    private void mapToEntity2(Doctor doctorDTO, DoctorDTO doctor) {
//        doctorDTO.setDoctorId(doctor.getId());
        doctorDTO.setUsername(doctor.getUsername());
        doctorDTO.setPassword(doctor.getPassword());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setSurname(doctor.getSurname());
        doctorDTO.setDateOfBrith(doctor.getDateOfBrith());
        doctorDTO.setRole(new Role(2, "doctor"));
        doctorDTO.setFieldOfExpertise(doctor.getFieldOfExpertise());

        System.out.println(doctorDTO);
    }

    public void update(final Integer doctorId, final DoctorDTO doctorDTO) {
        final Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(doctorDTO, doctor);
        doctorRepository.save(doctor);
    }

    public void delete(final Integer doctorId) {
        serviceRepository.deleteAllWithDoctorId(doctorId);
        examinationRepository.deleteAllWithDoctorId(doctorId);
        Doctor username = doctorRepository.getDoctorById(doctorId);
        System.out.println("####");
        System.out.println(username);
        System.out.println("$$$$");
        System.out.println(username.getUsername());
        userRepository.deleteByUsername(username.getUsername());
        doctorRepository.deleteById(doctorId);
    }

    private DoctorDTO mapToDTO(final Doctor doctor, final DoctorDTO doctorDTO) {
        doctorDTO.setDoctorId(doctor.getId());
        doctorDTO.setUsername(doctor.getUsername());
        doctorDTO.setPassword(doctor.getPassword());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setSurname(doctor.getSurname());
        doctorDTO.setDateOfBrith(doctor.getDateOfBrith());
        doctorDTO.setRoleId(doctor.getRole().getId());
        doctorDTO.setFieldOfExpertise(doctor.getFieldOfExpertise());
//        doctorDTO.setDoctor(doctor.getExamination() == null ? null : doctor.getExamination().getId());
        return doctorDTO;
    }

    private Doctor mapToEntity(final DoctorDTO doctorDTO, final Doctor doctor) {
        doctor.setUsername(doctorDTO.getUsername());
        doctor.setPassword(doctorDTO.getPassword());
        doctor.setName(doctorDTO.getName());
        doctor.setSurname(doctorDTO.getSurname());
        doctor.setDateOfBrith(doctorDTO.getDateOfBrith());
        doctor.getRole().setId(doctorDTO.getRoleId());
        doctor.setFieldOfExpertise(doctorDTO.getFieldOfExpertise());
//        final Examination examination = doctorDTO.getDoctor() == null ? null : examinationRepository.findById(doctorDTO.getDoctor())
//                .orElseThrow(() -> new NotFoundException("Doctor not found"));
//        examination.setDoctor(doctor);
        return doctor;
    }

    public Doctor getByUsername(String username) {
        return doctorRepository.getDoctorByUsername(username);
    }
}
