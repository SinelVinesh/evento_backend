package custom.springutils.service;

import custom.springutils.AuthenticatedDetails;
import custom.springutils.LoginEntity;

public interface ServiceLogin<E extends LoginEntity> {

    AuthenticatedDetails<E> login(E entity) throws Exception;
    boolean isConnected (String token) throws Exception;
    boolean logout (String token) throws Exception;
    E register(E entity) throws Exception;
    
}
