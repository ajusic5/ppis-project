package ba.unsa.etf.ppis_project.examination;

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
@RequestMapping(value = "/api/examinations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExaminationResource {

    private final ExaminationService examinationService;

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
            @RequestBody @Valid final ExaminationDTO examinationDTO) {
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
        examinationService.delete(examinationId);
        return ResponseEntity.noContent().build();
    }

}
