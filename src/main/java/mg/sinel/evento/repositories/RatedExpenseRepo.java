package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.RatedExpense;

public interface RatedExpenseRepo extends JpaRepository<RatedExpense, Long> {
}
