package ba.unsa.etf.ppis_project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
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
    private String name;

    @Column
    private String surname;

    @Size(max = 255)
    private String username;

    @Size(max = 255)
    private String password;

    @Column
    private LocalDate dateOfBirth;

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
        this.role = new Role(1, "patient");
    }

    public Patient(Integer id) {
        this.id = id;
        this.role = new Role(1, "patient");
    }

    public Patient(String username, String password, String name, String surname, LocalDate dateOfBirth, String insuranceCardNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.insuranceCardNumber = insuranceCardNumber;
        this.role = new Role("patient");
    }

    public Patient(String username, String password, String name, String surname, LocalDate dateOfBirth, String insuranceCardNumber, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.insuranceCardNumber = insuranceCardNumber;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", insuranceCardNumber='" + insuranceCardNumber + '\'' +
                ", role=" + role +
                '}';
    }
}
