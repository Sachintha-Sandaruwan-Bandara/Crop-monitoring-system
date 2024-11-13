package lk.ijse.cropmonitoringsystembackend.service;

import lk.ijse.cropmonitoringsystembackend.customObj.StaffResponse;
import lk.ijse.cropmonitoringsystembackend.dto.StaffDTO;

import java.util.List;

public interface StaffService {
    public void createStaff(StaffDTO staff);
    public StaffResponse getStaffById(String id);
    public void deleteStaff(String id);
    public void updateStaff(String id, StaffDTO updatedStaff);
    public List<StaffDTO> getAllStaff();

}
