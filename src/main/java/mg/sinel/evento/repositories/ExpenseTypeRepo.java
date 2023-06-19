package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.ExpenseType;

public interface ExpenseTypeRepo extends JpaRepository<ExpenseType, Long> {
}
