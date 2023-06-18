package mg.sinel.evento.repositories;

import mg.sinel.evento.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TokenRepo extends JpaRepository<Token, Long> {
    void deleteByUserId(Long id);
}
