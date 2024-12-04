package lk.ijse.cropmonitoringsystembackend.dao;

import lk.ijse.cropmonitoringsystembackend.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleDAO extends JpaRepository<VehicleEntity,String> {
    VehicleEntity getVehicleEntityByVehicleCode(String code);
}
