package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.RateType;

public interface RateTypeRepo extends JpaRepository<RateType, Long> {
}
