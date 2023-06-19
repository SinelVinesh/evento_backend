package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.EventStatus;

public interface EventStatusRepo extends JpaRepository<EventStatus, Long> {
}
