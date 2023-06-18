package custom.springutils.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@MappedSuperclass
@Setter
@Getter
public class TokenEntity {
    @Id
    protected String token;
    protected Timestamp expirationDate;
}
