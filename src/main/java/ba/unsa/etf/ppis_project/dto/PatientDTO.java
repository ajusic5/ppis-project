package ba.unsa.etf.ppis_project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatientDTO {

    private Integer patientId;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String surname;

    @Size(max = 255)
    private String username;

    @Size(max = 255)
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
    private LocalDate dateOfBirth;

    private Integer roleId;

    @Size(max = 255)
    private String insuranceCardNumber;

    public PatientDTO() {
    }

    public PatientDTO(String name, String surname, String username, String password, LocalDate dateOfBirth, String insuranceCardNumber, Integer roleId) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.insuranceCardNumber = insuranceCardNumber;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "patientId=" + patientId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", roleId=" + roleId +
                ", insuranceCardNumber='" + insuranceCardNumber + '\'' +
                '}';
    }


}
