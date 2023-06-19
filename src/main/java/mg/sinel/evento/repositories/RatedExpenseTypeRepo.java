package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.RatedExpenseType;

public interface RatedExpenseTypeRepo extends JpaRepository<RatedExpenseType, Long> {
}
