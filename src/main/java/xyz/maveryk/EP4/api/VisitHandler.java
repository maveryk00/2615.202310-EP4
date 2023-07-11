package xyz.maveryk.EP4.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.maveryk.EP4.dto.VisitDTO;
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


}
