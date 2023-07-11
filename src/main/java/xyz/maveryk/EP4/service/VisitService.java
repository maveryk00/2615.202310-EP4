package xyz.maveryk.EP4.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xyz.maveryk.EP4.dto.VisitDTO;
import xyz.maveryk.EP4.entity.Affiliate;
import xyz.maveryk.EP4.entity.Visit;
import xyz.maveryk.EP4.repository.AffiliateRepository;
import xyz.maveryk.EP4.repository.VisitRepository;
import xyz.maveryk.EP4.response.VisitResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final AffiliateRepository affiliateRepository;

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

                    return visitDTO;
                })
                .toList();
    }

    public VisitResponse save(VisitDTO visitDTO) {
        Affiliate affiliate = affiliateRepository.findByDni(visitDTO.getDni());

        if (affiliate == null) {
            return new VisitResponse(HttpStatus.NOT_FOUND.value(), "Afiliado no encontrado");
        } else {
            try {
                Visit newVisit = new Visit();
                newVisit.setLocal(visitDTO.getLocal());
                newVisit.setAffiliate(affiliate);
                newVisit.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(visitDTO.getDate()));
                newVisit.setTime(LocalTime.parse(visitDTO.getTime()));

                visitRepository.save(newVisit);
                return new VisitResponse(HttpStatus.CREATED.value(), "Visita registrada");
            } catch (ParseException e) {
                return new VisitResponse(HttpStatus.CONFLICT.value(), "Error");
            }
        }
    }

    public VisitResponse update(VisitDTO visitDTO) {
        Visit visit = visitRepository.findById(visitDTO.getId()).orElse(null);
        Affiliate affiliate = affiliateRepository.findByDni(visitDTO.getDni());

        if (affiliate == null) {
            return new VisitResponse(HttpStatus.NOT_FOUND.value(), "Afiliado no encontrado");
        } else {
            try {
                if (visit != null) {
                    visit.setLocal(visitDTO.getLocal());
                    visit.setAffiliate(affiliate);
                    visit.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(visitDTO.getDate()));
                    visit.setTime(LocalTime.parse(visitDTO.getTime()));

                    visitRepository.save(visit);
                    return new VisitResponse(HttpStatus.OK.value(), "Visita actualizada");
                }
                else {
                    return new VisitResponse(HttpStatus.NOT_FOUND.value(), "Visita no encontrada");
                }
            } catch (ParseException e) {
                return new VisitResponse(HttpStatus.CONFLICT.value(), "Error");
            }
        }
    }

    public VisitResponse delete(Long id) {
        Optional<Visit> visit = visitRepository.findById(id);
        if (visit.isPresent()) {
            visitRepository.deleteById(id);
            return new VisitResponse(HttpStatus.OK.value(), "Visita eliminada");
        }
        else {
            return new VisitResponse(HttpStatus.NOT_FOUND.value(), "Visita no encontrada");
        }
    }

}
