package ba.unsa.etf.ppis_project.doctor;

import ba.unsa.etf.ppis_project.model.Doctor;
import ba.unsa.etf.ppis_project.model.Examination;
import ba.unsa.etf.ppis_project.examination.ExaminationRepository;
import ba.unsa.etf.ppis_project.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ExaminationRepository examinationRepository;

    public DoctorService(final DoctorRepository doctorRepository,
            final ExaminationRepository examinationRepository) {
        this.doctorRepository = doctorRepository;
        this.examinationRepository = examinationRepository;
    }

    public List<DoctorDTO> findAll() {
        final List<Doctor> doctors = doctorRepository.findAll(Sort.by("id"));
        return doctors.stream()
                .map((doctor) -> mapToDTO(doctor, new DoctorDTO()))
                .toList();
    }

    public DoctorDTO get(final Integer doctorId) {
        return doctorRepository.findById(doctorId)
                .map(doctor -> mapToDTO(doctor, new DoctorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final DoctorDTO doctorDTO) {
        final Doctor doctor = new Doctor();
        mapToEntity(doctorDTO, doctor);
        return doctorRepository.save(doctor).getId();
    }

    public void update(final Integer doctorId, final DoctorDTO doctorDTO) {
        final Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(doctorDTO, doctor);
        doctorRepository.save(doctor);
    }

    public void delete(final Integer doctorId) {
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

}
