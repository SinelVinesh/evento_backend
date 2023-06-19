package mg.sinel.evento.repositories;

import mg.sinel.evento.models.EventExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventExpenseRepo extends JpaRepository<EventExpense, Long> {
    public void deleteByEventId(Long eventId);
}
