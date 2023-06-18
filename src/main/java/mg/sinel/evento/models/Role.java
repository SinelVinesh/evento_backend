package mg.sinel.evento.models;

import custom.springutils.model.HasName;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Table(name = "user_type")
public class Role extends HasName implements GrantedAuthority {

    public Role() {
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }

    public void setAuthority(String authority) {
        this.setName(authority);
    }
}
