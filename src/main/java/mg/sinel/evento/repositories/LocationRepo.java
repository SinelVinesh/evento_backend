package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.Location;

public interface LocationRepo extends JpaRepository<Location, Long> {
}
