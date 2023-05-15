package ba.unsa.etf.ppis_project.examination;


import ba.unsa.etf.ppis_project.doctor.DoctorRepository;
import ba.unsa.etf.ppis_project.model.Doctor;
import ba.unsa.etf.ppis_project.model.Examination;
import ba.unsa.etf.ppis_project.model.Patient;
import ba.unsa.etf.ppis_project.patient.PatientRepository;
import ba.unsa.etf.ppis_project.service.ServiceRepository;
import ba.unsa.etf.ppis_project.util.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class ExaminationService {

    private final ExaminationRepository examinationRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;


    public ExaminationService(final ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    public List<ExaminationDTO> findAll() {
        final List<Examination> examinations = examinationRepository.findAll(Sort.by("examinationId"));
        return examinations.stream()
                .map((examination) -> mapToDTO(examination, new ExaminationDTO()))
                .toList();
    }

    public List<ExaminationDTO> getAllExaminationsPatient(Integer patientId) {
        final List<Examination> examinations = examinationRepository.findAllWithPatientId(patientId);
        return examinations.stream()
                .map((examination) -> mapToDTO2(examination, new ExaminationDTO()))
                .toList();
    }


    public ExaminationDTO get(final Integer examinationId) {
        return examinationRepository.findById(examinationId)
                .map(examination -> mapToDTO2(examination, new ExaminationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ExaminationDTO examinationDTO) {
        final Examination examination = new Examination();
        mapToEntity(examinationDTO, examination);
        return examinationRepository.save(examination).getId();
    }

    public void update(final Integer examinationId, final ExaminationDTO examinationDTO) {
        final Examination examination = examinationRepository.findById(examinationId)
                .orElseThrow(NotFoundException::new);
        mapToEntity2(examinationDTO, examination);
        examinationRepository.save(examination);
    }

    private Examination mapToEntity2(ExaminationDTO examinationDTO, Examination examination) {

        examination.setPatient(patientRepository.getReferenceById(examinationDTO.getPatientId()));
        examination.setDoctor(doctorRepository.getReferenceById(examinationDTO.getDoctorId()));
        examination.setTypeOfExamination(examinationDTO.getTypeOfExamination());
        examination.setDateAndTimeOfAppointment(examinationDTO.getDateAndTimeOfAppointment());
        examination.setDateAndTimeOfReservation(examinationDTO.getDateAndTimeOfReservation());
        examination.setDiagnosis(examinationDTO.getDiagnosis());
        examination.setTherapy(examinationDTO.getTherapy());
        examination.setSuccessful(examinationDTO.getSuccessful());
        examination.setArchived(examinationDTO.getArchived());
        return examination;
    }


    public void delete(final Integer examinationId) {
        examinationRepository.deleteById(examinationId);
    }

    public String cancelAppointment(final Integer examinationId){
        Optional<Examination> e = examinationRepository.findById(examinationId);
        if (e.get() == null){
            throw new IllegalArgumentException("No examination with this id");
        }
        else{
            Examination ex = e.get();
            if(ex.getDateAndTimeOfReservation().plusDays(1).isBefore(LocalDateTime.now())){
                ex.setArchived(true);
                ex.setSuccessful(false);
                examinationRepository.cancelAppointment(ex.getId());
                return "Appointment cancelled successfully!";
            }
        }
        return "Cannot cancel appointment because it's been more than 24 hours since reservation!";
    }

    private ExaminationDTO mapToDTO(final Examination examination,
            final ExaminationDTO examinationDTO) {
        examinationDTO.setExaminationId(examination.getId());
       examinationDTO.setPatientId(examination.getPatient().getId());
        examinationDTO.setDoctorId(examination.getDoctor().getId());
        examinationDTO.setTypeOfExamination(examination.getTypeOfExamination());
        examinationDTO.setDateAndTimeOfAppointment(examination.getDateAndTimeOfAppointment());
        examinationDTO.setDateAndTimeOfReservation(examination.getDateAndTimeOfReservation());
        examinationDTO.setDiagnosis(examination.getDiagnosis());
        examinationDTO.setTherapy(examination.getTherapy());
        examinationDTO.setSuccessful(examination.getSuccessful());
        examinationDTO.setArchived(examination.getArchived());
        return examinationDTO;
    }

    private ExaminationDTO mapToDTO2(final Examination examination,
                                    final ExaminationDTO examinationDTO) {
        examinationDTO.setExaminationId(examination.getId());
        examinationDTO.setPatientId(examination.getPatient().getId());
//        examinationDTO.setDoctorId(examination.getDoctor().getId());
        examinationDTO.setTypeOfExamination(examination.getTypeOfExamination());
        examinationDTO.setDateAndTimeOfAppointment(examination.getDateAndTimeOfAppointment());
        examinationDTO.setDateAndTimeOfReservation(examination.getDateAndTimeOfReservation());
        examinationDTO.setDiagnosis(examination.getDiagnosis());
        examinationDTO.setTherapy(examination.getTherapy());
        examinationDTO.setSuccessful(examination.getSuccessful());
        examinationDTO.setArchived(examination.getArchived());
        return examinationDTO;
    }


    private Examination mapToEntity(final ExaminationDTO examinationDTO,
            final Examination examination) {
        examination.setPatient(patientRepository.getReferenceById(examinationDTO.getPatientId()));
//        examination.setDoctorId(examinationDTO.getDoctorId());
        examination.setTypeOfExamination(examinationDTO.getTypeOfExamination());
        examination.setDateAndTimeOfAppointment(examinationDTO.getDateAndTimeOfAppointment());
        examination.setDateAndTimeOfReservation(examinationDTO.getDateAndTimeOfReservation());
        examination.setDiagnosis(examinationDTO.getDiagnosis());
        examination.setTherapy(examinationDTO.getTherapy());
        examination.setSuccessful(examinationDTO.getSuccessful());
        examination.setArchived(examinationDTO.getArchived());
        return examination;
    }

    public List<Examination> getAllExaminationsDoctorCanDo(Integer doctorId) {
        List<Integer> list = serviceRepository.findAllWithDoctorId(doctorId);

        List<String> services = new ArrayList<>();


        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
            services.addAll(serviceRepository.findServiceByServiceId(list.get(i)));
            System.out.println(services);
        }


        List<Examination> examinationDTOS = new ArrayList<>();


        for (int i = 0; i < services.size(); i++){
            examinationDTOS.addAll(examinationRepository.findAllExaminationsServices(services.get(i)));
            System.out.println(examinationDTOS);
        }
        return examinationDTOS;
    }
}
