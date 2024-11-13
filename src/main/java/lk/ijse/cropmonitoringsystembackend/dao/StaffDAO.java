package lk.ijse.cropmonitoringsystembackend.dao;

import lk.ijse.cropmonitoringsystembackend.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDAO  extends JpaRepository<StaffEntity,String> {
  StaffEntity getStaffEntityById(String id);
}
