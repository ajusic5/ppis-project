package ba.unsa.etf.ppis_project.examination;

import ba.unsa.etf.ppis_project.doctor.Doctor;
import ba.unsa.etf.ppis_project.patient.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Examination {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "patient_id", nullable = true)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "doctor_id", nullable = true)
    private Doctor doctor;


    @Column
    private String typeOfExamination;

    @Column
    private LocalDateTime dateAndTimeOfAppointment;

    @Column
    private LocalDateTime dateAndTimeOfReservation;

    @Column
    private String diagnosis;

    @Column
    private String therapy;

    @Column
    private Boolean successful;

    @Column
    private Boolean archived;


    public Examination() {
    }

    public Examination(String typeOfExamination, LocalDateTime dateAndTimeOfAppointment, LocalDateTime dateAndTimeOfReservation, String diagnosis, String therapy, Boolean successful, Boolean archived, Patient patient, Doctor doctor) {
        this.typeOfExamination = typeOfExamination;
        this.dateAndTimeOfAppointment = dateAndTimeOfAppointment;
        this.dateAndTimeOfReservation = dateAndTimeOfReservation;
        this.diagnosis = diagnosis;
        this.therapy = therapy;
        this.successful = successful;
        this.archived = archived;
        this.patient = patient;
        this.doctor = doctor;
    }
}
