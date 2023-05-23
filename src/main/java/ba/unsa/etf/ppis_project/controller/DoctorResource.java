package ba.unsa.etf.ppis_project.controller;

import ba.unsa.etf.ppis_project.dto.DoctorDTO;
import ba.unsa.etf.ppis_project.model.Doctor;
import ba.unsa.etf.ppis_project.model.Patient;
import ba.unsa.etf.ppis_project.service.DoctorService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
public class DoctorResource {

    private final DoctorService doctorService;

    public DoctorResource(final DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> getDoctor(
            @PathVariable(name = "doctorId") final Integer doctorId) {
        return ResponseEntity.ok(doctorService.get(doctorId));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<Doctor> getDoctor(
            @PathVariable(name = "username") final String username) {
        return ResponseEntity.ok(doctorService.getByUsername(username));
    }
    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createDoctor(@RequestBody JsonNode req) {

        DoctorDTO doctorDTO = new DoctorDTO(req.get("username").asText(),
                                            req.get("password").asText(),
                                            req.get("name").asText(),
                                            req.get("surname").asText(),
                                            LocalDate.parse(req.get("dateOfBirth").asText()),
                                            2,
                                            req.get("fieldOfExpertise").asText()
                );
        System.out.println(doctorDTO.toString());
        final Integer createdDoctorId = doctorService.create(doctorDTO);
        return new ResponseEntity<>(createdDoctorId, HttpStatus.CREATED);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<Void> updateDoctor(
            @PathVariable(name = "doctorId") final Integer doctorId,
            @RequestBody @Valid final DoctorDTO doctorDTO) {
        doctorService.update(doctorId, doctorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{doctorId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable(name = "doctorId") final Integer doctorId) {
        doctorService.delete(doctorId);
        return ResponseEntity.noContent().build();
    }

}
