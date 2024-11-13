package lk.ijse.cropmonitoringsystembackend.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cropmonitoringsystembackend.customObj.StaffErrorResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.StaffResponse;
import lk.ijse.cropmonitoringsystembackend.dao.StaffDAO;
import lk.ijse.cropmonitoringsystembackend.dto.StaffDTO;
import lk.ijse.cropmonitoringsystembackend.entity.StaffEntity;
import lk.ijse.cropmonitoringsystembackend.exception.DataPersistFailedException;
import lk.ijse.cropmonitoringsystembackend.exception.InvalidStaffDataException;
import lk.ijse.cropmonitoringsystembackend.exception.StaffNotFoundException;
import lk.ijse.cropmonitoringsystembackend.service.StaffService;
import lk.ijse.cropmonitoringsystembackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    private final StaffDAO staffDAO;
    private final Mapping mapping;

    @Autowired
    public StaffServiceImpl(StaffDAO staffDAO,Mapping mapping) {
        this.staffDAO = staffDAO;
        this.mapping=mapping;
    }

    @Override
    public void createStaff(StaffDTO staff) {
        if (staff.getFirstName() == null) {
            throw new InvalidStaffDataException(" First Name are required.");
        }
        StaffEntity savedMember = staffDAO.save(mapping.convertToStaffEntity(staff));
        if(savedMember == null){
            throw new DataPersistFailedException("Cannot save member");
        }
    }

    @Override
    public StaffResponse getStaffById(String id) {
        if(staffDAO.existsById(id)){
            StaffEntity staffEntityById = staffDAO.getStaffEntityById(id);
            return mapping.convertToStaffDTO(staffEntityById);
        }else {
            return new StaffErrorResponse(0, "member not found");
        }
    }

    public List<StaffDTO> getAllStaff() {
        return mapping.convertPassengerToDTOList(staffDAO.findAll());
    }

    @Override
    public void updateStaff(String id, StaffDTO updatedStaff) {
        Optional<StaffEntity> tmpMember = staffDAO.findById(id); // Find by `id` parameter, not `updatedStaff.getId()`

        if (tmpMember.isEmpty()) {
            throw new StaffNotFoundException("Member not found");
        } else {
            StaffEntity staffEntity = mapping.convertToStaffEntity(updatedStaff);
            staffEntity.setId(id);
            staffDAO.save(staffEntity);
        }
    }

    @Override
    public void deleteStaff(String id) {
        if (!staffDAO.existsById(id)) {
            throw new StaffNotFoundException("Cannot delete: Staff with ID " + id + " not found.");
        }
        staffDAO.deleteById(id);
    }
}
