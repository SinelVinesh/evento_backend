package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.SeatCategory;

public interface SeatCategoryRepo extends JpaRepository<SeatCategory, Long> {
}
