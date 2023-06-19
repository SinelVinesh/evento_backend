package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.EventExpense;

public interface EventExpenseRepo extends JpaRepository<EventExpense, Long> {
}
