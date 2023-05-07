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

}
