package lk.ijse.cropmonitoringsystembackend.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cropmonitoringsystembackend.customObj.EquipmentErrorResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.EquipmentResponse;
import lk.ijse.cropmonitoringsystembackend.dao.EquipmentDAO;
import lk.ijse.cropmonitoringsystembackend.dto.EquipmentDTO;
import lk.ijse.cropmonitoringsystembackend.entity.EquipmentEntity;
import lk.ijse.cropmonitoringsystembackend.exception.*;
import lk.ijse.cropmonitoringsystembackend.service.EquipmentService;
import lk.ijse.cropmonitoringsystembackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final Mapping mapping;
    private final EquipmentDAO equipmentDAO;

    @Autowired
    public EquipmentServiceImpl(Mapping mapping, EquipmentDAO equipmentDAO) {
        this.mapping = mapping;
        this.equipmentDAO = equipmentDAO;
    }

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        if (equipmentDTO.getName() == null) {
            throw new InvalidDataException(" Equipment Name are required.");
        }
        EquipmentEntity equipmentEntity = equipmentDAO.save(mapping.convertToEquipmentEntity(equipmentDTO));
        if (equipmentEntity == null) {
            throw new DataPersistFailedException("Cannot save equipment");
        }
    }

    @Override
    public EquipmentResponse getEquipmentById(String id) {
        if(equipmentDAO.existsById(id)){
            EquipmentEntity equipmentEntity = equipmentDAO.getEquipmentEntityByEquipmentId(id);
            return mapping.convertToEquipmentDTO(equipmentEntity);
        }else {
            return new EquipmentErrorResponse(0, "Equipment not found");
        }
    }

    @Override
    public void deleteEquipment(String id) {
        if (!equipmentDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete: Equipment with id " + id + " not found.");
        }
        equipmentDAO.deleteById(id);
    }

    @Override
    public void updatEquipment(String id, EquipmentDTO updatedEquipment) {
        Optional<EquipmentEntity> tmpEquipment = equipmentDAO.findById(id);

        if (tmpEquipment.isEmpty()) {
            throw new StaffNotFoundException("Equipment not found");
        } else {
            EquipmentEntity equipmentEntity = mapping.convertToEquipmentEntity(updatedEquipment);
            equipmentEntity.setEquipmentId(id);
            equipmentDAO.save(equipmentEntity);
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return mapping.convertEquipmentToDTOList(equipmentDAO.findAll());
    }
}
