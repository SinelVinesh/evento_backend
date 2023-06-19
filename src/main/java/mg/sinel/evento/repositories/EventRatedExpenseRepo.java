package mg.sinel.evento.repositories;

import mg.sinel.evento.models.EventRatedExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRatedExpenseRepo extends JpaRepository<EventRatedExpense, Long> {
    public void deleteByEventId(Long eventId);
}
