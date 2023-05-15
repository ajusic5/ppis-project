package ba.unsa.etf.ppis_project.examination;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExaminationDTO {

    private Integer examinationId;

    private Integer patientId;

    private Integer doctorId;

    @Size(max = 255)
    private String typeOfExamination;

    private LocalDateTime dateAndTimeOfAppointment;

    private LocalDateTime dateAndTimeOfReservation;

    @Size(max = 255)
    private String diagnosis;

    @Size(max = 255)
    private String therapy;

    private Boolean successful;

    private Boolean archived;

    public ExaminationDTO() {
    }

    public ExaminationDTO(Integer patientId, LocalDateTime dateAndTimeOfAppointment, String typeOfExamination) {
        this.patientId  = patientId;
        this.dateAndTimeOfAppointment = dateAndTimeOfAppointment;
        this.dateAndTimeOfReservation = LocalDateTime.now();
        this.typeOfExamination = typeOfExamination;
        this.successful = false;
        this.archived = false;
    }

    public ExaminationDTO(Integer examinationId, Integer patientId, Integer doctorId, String typeOfExamination, LocalDateTime dateAndTimeOfAppointment, LocalDateTime dateAndTimeOfReservation, String diagnosis, String therapy, Boolean successful, Boolean archived) {
        this.examinationId = examinationId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.typeOfExamination = typeOfExamination;
        this.dateAndTimeOfAppointment = dateAndTimeOfAppointment;
        this.dateAndTimeOfReservation = dateAndTimeOfReservation;
        this.diagnosis = diagnosis;
        this.therapy = therapy;
        this.successful = successful;
        this.archived = archived;
    }
}
