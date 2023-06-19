package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.Event;

public interface EventRepo extends JpaRepository<Event, Long> {
}
