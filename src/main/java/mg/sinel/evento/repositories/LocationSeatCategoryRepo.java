package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.LocationSeatCategory;

public interface LocationSeatCategoryRepo extends JpaRepository<LocationSeatCategory, Long> {
}
