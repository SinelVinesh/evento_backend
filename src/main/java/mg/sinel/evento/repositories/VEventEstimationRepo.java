package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.VEventEstimation;

public interface VEventEstimationRepo extends JpaRepository<VEventEstimation, Long> {
}
