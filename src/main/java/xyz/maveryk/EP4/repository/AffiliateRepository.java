package xyz.maveryk.EP4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.maveryk.EP4.entity.Affiliate;

@Repository
public interface AffiliateRepository extends JpaRepository<Affiliate, Long> {

    Affiliate findByDni(String dni);

}
