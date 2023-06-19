package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.Material;

public interface MaterialRepo extends JpaRepository<Material, Long> {
}
