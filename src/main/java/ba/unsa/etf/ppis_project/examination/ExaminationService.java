package ba.unsa.etf.ppis_project.examination;

import ba.unsa.etf.ppis_project.util.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ExaminationService {

    private final ExaminationRepository examinationRepository;

    public ExaminationService(final ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    public List<ExaminationDTO> findAll() {
        final List<Examination> examinations = examinationRepository.findAll(Sort.by("examinationId"));
        return examinations.stream()
                .map((examination) -> mapToDTO(examination, new ExaminationDTO()))
                .toList();
    }

    public ExaminationDTO get(final Integer examinationId) {
        return examinationRepository.findById(examinationId)
                .map(examination -> mapToDTO(examination, new ExaminationDTO()))
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
        mapToEntity(examinationDTO, examination);
        examinationRepository.save(examination);
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
//        examinationDTO.setPatientId(examination.getPatientId());
//        examinationDTO.setDoctorId(examination.getDoctorId());
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
//        examination.setPatientId(examinationDTO.getPatientId());
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

}
