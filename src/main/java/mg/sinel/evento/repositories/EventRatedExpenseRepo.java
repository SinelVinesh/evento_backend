package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.EventRatedExpense;

public interface EventRatedExpenseRepo extends JpaRepository<EventRatedExpense, Long> {
}
