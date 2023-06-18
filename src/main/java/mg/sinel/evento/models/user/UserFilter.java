package mg.sinel.evento.models.user;

import lombok.Getter;
import lombok.Setter;
import mg.sinel.evento.models.Role;

@Getter
@Setter
public class UserFilter {
    private Role role;
    private String ilike_username;
    private String ilike_email;

    public void setIlike_username(String ilike_username) {
        this.ilike_username = "%" + ilike_username + "%";
    }

    public String getIlike_username() {
        return ilike_username.replace("%", "");
    }

    public void setIlike_email(String ilike_email) {
        this.ilike_email = "%" + ilike_email + "%";
    }

    public String getIlike_email() {
        return ilike_email.replace("%", "");
    }
}
