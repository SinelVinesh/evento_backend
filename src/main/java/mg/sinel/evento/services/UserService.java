package mg.sinel.evento.services;

import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import mg.sinel.evento.models.user.User;
import mg.sinel.evento.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User, UserRepo> {
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo repo, EntityManager manager, PasswordEncoder passwordEncoder) {
        super(repo, manager);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Class<?> getEntityClass() {
        return User.class;
    }

    @Override
    public User create(User obj) throws Exception {
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        return super.create(obj);
    }
}
