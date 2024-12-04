package lk.ijse.cropmonitoringsystembackend.service;

import lk.ijse.cropmonitoringsystembackend.customObj.CropResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.EquipmentResponse;
import lk.ijse.cropmonitoringsystembackend.dto.CropDTO;
import lk.ijse.cropmonitoringsystembackend.dto.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    public void saveEquipment(EquipmentDTO equipmentDTO);
    public EquipmentResponse getEquipmentById(String id);
    public void deleteEquipment(String id);
    public void updatEquipment(String id, EquipmentDTO updatedEquipment);
    public List<EquipmentDTO> getAllEquipments();
}
