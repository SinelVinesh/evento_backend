package mg.sinel.evento.repositories;

import mg.sinel.evento.models.EventSeatCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSeatCategoryRepo extends JpaRepository<EventSeatCategory, Long> {
    public void deleteByEventId(Long eventId);
}
