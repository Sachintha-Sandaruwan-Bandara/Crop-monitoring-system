package lk.ijse.cropmonitoringsystembackend.dao;

import lk.ijse.cropmonitoringsystembackend.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDAO extends JpaRepository<LogEntity,String> {
    LogEntity getLogEntityByLogCode(String code);
}
