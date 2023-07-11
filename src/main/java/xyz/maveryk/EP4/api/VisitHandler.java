package xyz.maveryk.EP4.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.maveryk.EP4.dto.VisitDTO;
import xyz.maveryk.EP4.response.VisitResponse;
import xyz.maveryk.EP4.service.VisitService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/visits")
public class VisitHandler {

    private final VisitService visitService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<VisitDTO>> getAll() {
        return ResponseEntity.ok(visitService.getAll());
    }

    @PostMapping({"", "/"})
    public ResponseEntity<VisitResponse> register(@RequestBody VisitDTO visitDTO) {
        VisitResponse response = visitService.save(visitDTO);
        return ResponseEntity.status(HttpStatus.valueOf(response.code)).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitResponse> update(@PathVariable("id") Long id, @RequestBody VisitDTO visitDTO) {
        visitDTO.setId(id);
        VisitResponse response = visitService.update(visitDTO);
        return ResponseEntity.status(HttpStatus.valueOf(response.code)).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VisitResponse> delete(@PathVariable("id") Long id) {
        VisitResponse response = visitService.delete(id);
        return ResponseEntity.status(HttpStatus.valueOf(response.code)).body(response);
    }

}
