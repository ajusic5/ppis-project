package ba.unsa.etf.ppis_project.patient;

import ba.unsa.etf.ppis_project.examination.Examination;
import ba.unsa.etf.ppis_project.role.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Patient {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private LocalDate dateOfBrith;

    @Column
    private String insuranceCardNumber;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "patient_id", unique = true)
//    private Examination examination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "roleId", nullable = true)
    private Role role;

    public Patient() {
    }


    public Patient(String username, String password, String name, String surname, LocalDate dateOfBrith, String insuranceCardNumber, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBrith = dateOfBrith;
        this.insuranceCardNumber = insuranceCardNumber;
        this.role = role;
    }
}
