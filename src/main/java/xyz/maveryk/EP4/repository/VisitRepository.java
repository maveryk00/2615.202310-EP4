package xyz.maveryk.EP4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.maveryk.EP4.entity.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
