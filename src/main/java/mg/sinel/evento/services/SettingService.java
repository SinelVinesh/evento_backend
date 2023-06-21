package mg.sinel.evento.services;

import mg.sinel.evento.repositories.SettingRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import java.math.BigDecimal;
import mg.sinel.evento.models.Setting;


@Service
public class SettingService extends CrudService<Setting, SettingRepo> {

    public SettingService(SettingRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Setting> getEntityClass() {
        return Setting.class;
    }

}
