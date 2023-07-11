package xyz.maveryk.EP4.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.maveryk.EP4.dto.VisitDTO;
import xyz.maveryk.EP4.repository.VisitRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public List<VisitDTO> getAll() {
        return visitRepository.findAll()
                .stream()
                .map(visit -> {
                    VisitDTO visitDTO = new VisitDTO();
                    visitDTO.setId(visit.getId());
                    visitDTO.setLocal(visit.getLocal());
                    visitDTO.setDni(visit.getAffiliate().getDni());
                    visitDTO.setDate(visit.getDate().toString());
                    visitDTO.setTime(visit.getTime().toString());

                    return  visitDTO;
                })
                .toList();
    }

}
