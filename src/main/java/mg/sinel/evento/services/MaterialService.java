package mg.sinel.evento.services;

import mg.sinel.evento.repositories.MaterialRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.lang.String;
import java.lang.Integer;
import mg.sinel.evento.models.Material;


@Service
public class MaterialService extends CrudService<Material, MaterialRepo> {

    public MaterialService(MaterialRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Material> getEntityClass() {
        return Material.class;
    }

}
