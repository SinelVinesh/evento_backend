package mg.sinel.evento.services;

import mg.sinel.evento.repositories.RateTypeRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import mg.sinel.evento.models.RateType;


@Service
public class RateTypeService extends CrudService<RateType, RateTypeRepo> {

    public RateTypeService(RateTypeRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<RateType> getEntityClass() {
        return RateType.class;
    }

}
