package ba.unsa.etf.ppis_project.service;

import ba.unsa.etf.ppis_project.dto.PatientDTO;
import ba.unsa.etf.ppis_project.repos.ExaminationRepository;
import ba.unsa.etf.ppis_project.model.Patient;
import ba.unsa.etf.ppis_project.model.User;
import ba.unsa.etf.ppis_project.repos.PatientRepository;
import ba.unsa.etf.ppis_project.repos.UserRepository;
import ba.unsa.etf.ppis_project.util.NotFoundException;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final ExaminationRepository examinationRepository;
    private final UserRepository userRepository;

    public PatientService(final PatientRepository patientRepository,
            final ExaminationRepository examinationRepository,
            final UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.examinationRepository = examinationRepository;
        this.userRepository = userRepository;
    }

    public List<PatientDTO> findAll() {
        final List<Patient> patients = patientRepository.findAll(Sort.by("id"));
        return patients.stream()
                .map((patient) -> mapToDTO(patient, new PatientDTO()))
                .toList();
    }

    public PatientDTO get(final Integer patientId) {
        return patientRepository.findById(patientId)
                .map(patient -> mapToDTO(patient, new PatientDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final PatientDTO patientDTO) {
        final Patient patient = new Patient();
        mapToEntity(patientDTO, patient);
        User user = new User (patient.getUsername(), patient.getPassword(), "patient");
        userRepository.save(user);
        System.out.println(user.toString());
        return patientRepository.save(patient).getId();
    }

    public void update(final Integer patientId, final PatientDTO patientDTO) {
        final Patient patient = patientRepository.findById(patientId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(patientDTO, patient);
        patientRepository.save(patient);
    }

    public void delete(final Integer patientId) {
        patientRepository.deleteById(patientId);
    }

    private PatientDTO mapToDTO(final Patient patient, final PatientDTO patientDTO) {
        patientDTO.setPatientId(patient.getId());
        patientDTO.setUsername(patient.getUsername());
        patientDTO.setPassword(patient.getPassword());
        patientDTO.setName(patient.getName());
        patientDTO.setSurname(patient.getSurname());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setRoleId(patient.getRole().getId());
        patientDTO.setInsuranceCardNumber(patient.getInsuranceCardNumber());
//        patientDTO.setPatient(patient.getExamination() == null ? null : patient.getExamination().getId());
        return patientDTO;
    }

    private Patient mapToEntity(final PatientDTO patientDTO, final Patient patient) {
        patient.setUsername(patientDTO.getUsername());
        patient.setPassword(patientDTO.getPassword());
        patient.setName(patientDTO.getName());
        patient.setSurname(patientDTO.getSurname());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
//        patient.setRole(patientDTO.getRoleId());
        patient.getRole().setId(patientDTO.getRoleId());
        patient.getRole().setRoleName("Patient");
        patient.setInsuranceCardNumber(patientDTO.getInsuranceCardNumber());
        System.out.println(patient);
//        final Examination examination = patientDTO.getPatient() == null ? null : examinationRepository.findById(patientDTO.getPatient())
//                .orElseThrow(() -> new NotFoundException("patient not found"));
//        examination.setPatient(patient);
        return patient;
    }

    public Patient getByUsername(String username) {
       return patientRepository.getPatientByUsername(username);
    }
}
