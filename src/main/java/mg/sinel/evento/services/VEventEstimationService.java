package mg.sinel.evento.services;

import mg.sinel.evento.repositories.VEventEstimationRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Boolean;
import java.lang.String;
import mg.sinel.evento.models.VEventEstimation;


@Service
public class VEventEstimationService extends CrudService<VEventEstimation, VEventEstimationRepo> {

    public VEventEstimationService(VEventEstimationRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VEventEstimation> getEntityClass() {
        return VEventEstimation.class;
    }

}
