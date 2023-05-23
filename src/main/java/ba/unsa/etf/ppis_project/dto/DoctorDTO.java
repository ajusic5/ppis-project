package ba.unsa.etf.ppis_project.dto;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DoctorDTO {

    private Integer doctorId;

    @Size(max = 255)
    private String username;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String surname;

    private LocalDate dateOfBrith;

    private Integer roleId;

    @Size(max = 255)
    private String fieldOfExpertise;

    public DoctorDTO() {
    }

    public DoctorDTO(String username, String password, String name, String surname, LocalDate dateOfBrith, Integer roleId, String fieldOfExpertise) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBrith = dateOfBrith;
        this.roleId = roleId;
        this.fieldOfExpertise = fieldOfExpertise;
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "doctorId=" + doctorId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBrith=" + dateOfBrith +
                ", roleId=" + roleId +
                ", fieldOfExpertise='" + fieldOfExpertise + '\'' +
                '}';
    }

    //    private Integer doctor;

}
