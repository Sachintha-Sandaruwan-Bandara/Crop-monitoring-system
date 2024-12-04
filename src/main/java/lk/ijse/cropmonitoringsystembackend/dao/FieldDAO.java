package lk.ijse.cropmonitoringsystembackend.dao;

import lk.ijse.cropmonitoringsystembackend.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldDAO extends JpaRepository<FieldEntity,String> {
    FieldEntity getFieldEntityByFieldCode(String code);
}
