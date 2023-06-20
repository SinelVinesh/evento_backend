package mg.sinel.evento.services;

import mg.sinel.evento.repositories.SeatCategoryRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import mg.sinel.evento.models.SeatCategory;


@Service
public class SeatCategoryService extends CrudService<SeatCategory, SeatCategoryRepo> {

    public SeatCategoryService(SeatCategoryRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<SeatCategory> getEntityClass() {
        return SeatCategory.class;
    }

}
