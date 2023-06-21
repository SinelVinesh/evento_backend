package mg.sinel.evento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.sinel.evento.models.Setting;

public interface SettingRepo extends JpaRepository<Setting, Long> {
}
