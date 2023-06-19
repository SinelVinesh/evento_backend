package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.LocationType;

public interface LocationTypeRepo extends JpaRepository<LocationType, Long> {
}
