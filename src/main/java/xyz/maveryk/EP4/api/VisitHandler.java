package xyz.maveryk.EP4.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.maveryk.EP4.dto.VisitDTO;
import xyz.maveryk.EP4.response.VisitCreateResponse;
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
    public ResponseEntity<VisitCreateResponse> register(@RequestBody VisitDTO visitDTO) {
        VisitCreateResponse response = visitService.save(visitDTO);
        return ResponseEntity.status(HttpStatus.valueOf(response.code)).body(response);
    }


}
