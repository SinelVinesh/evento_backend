package custom.springutils.service;

import custom.springutils.AuthenticatedDetails;
import custom.springutils.LoginEntity;
import custom.springutils.LoginRepo;
import custom.springutils.exception.LoginException;
import custom.springutils.exception.RegisterException;
import custom.springutils.model.TokenEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class LoginService<E extends LoginEntity, R extends LoginRepo<E>,
        T extends TokenEntity, TR extends JpaRepository<T, String>> implements ServiceLogin<E> {
    protected R repo;
    protected TR tokenRepo;

    protected Class<T> tokenClass;

    // seconds
    protected int getTokenDuration () {
        return 3600;
    }

    public LoginService(R repo, TR tokenRepo, Class<T> tokenClass)
    {
        this.tokenClass = tokenClass;
        this.repo = repo;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public AuthenticatedDetails<E> login(E entity) throws Exception {
        E usr = getLoginDetails(entity);
        T token = createToken(usr);
        tokenRepo.save(token);
        return new AuthenticatedDetails<>(token, usr);
    }

    @Override
    public boolean logout(String tokenValue) throws Exception {
        T token = tokenRepo.findById(tokenValue).orElse(null);
        if(token == null){
            return true;
        }
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        if(token.getExpirationDate().compareTo(now) > 0){
            token.setExpirationDate(now);
            tokenRepo.save(token);
        }
        return true;
    }

    @Override
    public boolean isConnected(String tokenValue) throws Exception {
        T token = tokenRepo.findById(tokenValue).orElse(null);
        if(token == null){
            return false;
        }
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        return token.getExpirationDate().compareTo(now) > 0;
    }

    @Override
    public E register(E entity) throws Exception {
        List<E> tmp = repo.findByEmail(entity.getEmail());
        if(!tmp.isEmpty()){
            throw new RegisterException("This email already has an account linked with.");
        }
        return this.repo.save(entity);
    }

    protected T createToken (E entity) throws InstantiationException, IllegalAccessException {
        T token =  tokenClass.newInstance();

        String tokenValue = entity.getEmail() + entity.getPassword() + LocalDateTime.now();
        tokenValue = DigestUtils.sha1Hex(tokenValue.getBytes());
        Timestamp expirationDate = Timestamp.valueOf(LocalDateTime.now().plusSeconds(getTokenDuration()));

        token.setExpirationDate(expirationDate);
        token.setToken(tokenValue);

        return token;
    }


    private E getLoginDetails(E entity) throws LoginException {
        List<E> list = repo.findByEmail(entity.getEmail());
        if (list.size() == 0) throw new LoginException("user not found");
        for (E elt : list) {
            if (elt.getPassword().equals(entity.getPassword())) {
                return elt;
            }
        }
        throw new LoginException("wrong password");
    }
}
