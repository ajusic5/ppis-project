package ba.unsa.etf.ppis_project.examination;

import ba.unsa.etf.ppis_project.model.Examination;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/examinations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExaminationResource {

    private final ExaminationService examinationService;
    @Autowired
    private ExaminationRepository examinationRepository;

    public ExaminationResource(final ExaminationService examinationService) {
        this.examinationService = examinationService;
    }

    @GetMapping
    public ResponseEntity<List<ExaminationDTO>> getAllExaminations() {
        return ResponseEntity.ok(examinationService.findAll());
    }

    @GetMapping("/{examinationId}")
    public ResponseEntity<ExaminationDTO> getExamination(
            @PathVariable(name = "examinationId") final Integer examinationId) {
        return ResponseEntity.ok(examinationService.get(examinationId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createExamination(
            @RequestBody JsonNode req) {
//        System.out.println(req.get("patientId").asInt());
//        System.out.println("#");
        ExaminationDTO examinationDTO = new ExaminationDTO(req.get("patientId").asInt(),
                                                            LocalDateTime.parse(req.get("dateAndTimeOfAppointment").asText()),
                                                            req.get("typeOfExamination").asText()
                );
        final Integer createdExaminationId = examinationService.create(examinationDTO);
        return new ResponseEntity<>(createdExaminationId, HttpStatus.CREATED);
    }

    @PutMapping("/{examinationId}")
    public ResponseEntity<Void> updateExamination(
            @PathVariable(name = "examinationId") final Integer examinationId,
            @RequestBody @Valid final ExaminationDTO examinationDTO) {
        examinationService.update(examinationId, examinationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{examinationId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteExamination(
            @PathVariable(name = "examinationId") final Integer examinationId) {

        Optional<Examination> e = examinationRepository.findById(examinationId);
        System.out.println(examinationId);
        if (e.get() == null){
            throw new IllegalArgumentException("No examination with this id");
        }
        else{
            Examination ex = e.get();
        //    System.out.println(ex.getDateAndTimeOfReservation().plusDays(1).isBefore(LocalDateTime.now().plusDays(1)));
            if(ex.getDateAndTimeOfReservation().plusDays(1).isBefore(LocalDateTime.now().plusDays(1))){
//                System.out.println("Hellou?!");
                ex.setArchived(true);
                ex.setSuccessful(false);
                examinationRepository.cancelAppointment(ex.getId());
                return ResponseEntity.noContent().build();
            }
        }
//        examinationService.delete(examinationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ExaminationDTO>> getAllExaminationsOfPatient(@PathVariable(name = "patientId") final Integer patientId){
        return ResponseEntity.ok(examinationService.getAllExaminationsPatient(patientId));

    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Examination>> getAllExaminationsDoctorCanDo(@PathVariable(name = "doctorId") final Integer doctorId){
        return ResponseEntity.ok(examinationService.getAllExaminationsDoctorCanDo(doctorId));

    }

}
