package ba.unsa.etf.ppis_project.patient;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/patients", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientResource {

    private final PatientService patientService;

    public PatientResource(final PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDTO> getPatient(
            @PathVariable(name = "patientId") final Integer patientId) {
        return ResponseEntity.ok(patientService.get(patientId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createPatient(@RequestBody @Valid final PatientDTO patientDTO) {
        final Integer createdPatientId = patientService.create(patientDTO);
        return new ResponseEntity<>(createdPatientId, HttpStatus.CREATED);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<Void> updatePatient(
            @PathVariable(name = "patientId") final Integer patientId,
            @RequestBody @Valid final PatientDTO patientDTO) {
        patientService.update(patientId, patientDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{patientId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePatient(
            @PathVariable(name = "patientId") final Integer patientId) {
        patientService.delete(patientId);
        return ResponseEntity.noContent().build();
    }

}
