package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.EventType;

public interface EventTypeRepo extends JpaRepository<EventType, Long> {
}
