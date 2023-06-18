package custom.springutils.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public class HasId {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
