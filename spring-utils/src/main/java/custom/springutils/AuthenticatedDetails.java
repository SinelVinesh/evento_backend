package custom.springutils;

import custom.springutils.model.TokenEntity;

public class AuthenticatedDetails<T> {

    private TokenEntity token;
    private T entity;

    public AuthenticatedDetails(TokenEntity token, T entity) {
        setToken(token);
        setEntity(entity);
    }

    public AuthenticatedDetails() {
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public TokenEntity getToken() {
        return token;
    }

    public void setToken(TokenEntity token) {
        this.token = token;
    }
}
