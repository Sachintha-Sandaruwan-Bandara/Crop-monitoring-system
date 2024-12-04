package lk.ijse.cropmonitoringsystembackend.service;

import lk.ijse.cropmonitoringsystembackend.customObj.FieldResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.StaffResponse;
import lk.ijse.cropmonitoringsystembackend.dto.FieldDTO;
import lk.ijse.cropmonitoringsystembackend.dto.StaffDTO;

import java.util.List;

public interface FieldService {
    public void createField(FieldDTO fieldDTO);
    public FieldResponse getFieldByCode(String code);
    public void deleteField(String code);
    public void updateField(String code, FieldDTO updatedField);
    public List<FieldDTO> getAllFields();
}
