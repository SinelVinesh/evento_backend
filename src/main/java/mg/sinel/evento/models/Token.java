package mg.sinel.evento.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import mg.sinel.evento.models.user.User;

@Getter
@Setter
@Entity
public class Token extends HasId {
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

    private String token;

    public Token() {
    }

    public Token(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
