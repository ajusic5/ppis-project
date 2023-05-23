package ba.unsa.etf.ppis_project.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private String role;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


}