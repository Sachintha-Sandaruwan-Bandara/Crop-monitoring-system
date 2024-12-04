package lk.ijse.cropmonitoringsystembackend.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cropmonitoringsystembackend.customObj.FieldErrorResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.FieldResponse;
import lk.ijse.cropmonitoringsystembackend.dao.FieldDAO;
import lk.ijse.cropmonitoringsystembackend.dto.FieldDTO;
import lk.ijse.cropmonitoringsystembackend.entity.FieldEntity;
import lk.ijse.cropmonitoringsystembackend.exception.*;
import lk.ijse.cropmonitoringsystembackend.service.FieldService;
import lk.ijse.cropmonitoringsystembackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    private final FieldDAO fieldDAO;
    private final Mapping mapping;

    @Autowired
    public FieldServiceImpl(FieldDAO fieldDAO,Mapping mapping) {
        this.fieldDAO=fieldDAO;
        this.mapping=mapping;
    }
    @Override
    public void createField(FieldDTO fieldDTO) {
        if (fieldDTO.getFieldName() == null) {
            throw new InvalidFieldDataException(" Field Name are required.");
        }
        FieldEntity savedField = fieldDAO.save(mapping.convertToFieldEntity(fieldDTO));
        if(savedField == null){
            throw new DataPersistFailedException("Cannot save Field");
        }
    }

    @Override
    public FieldResponse getFieldByCode(String code) {
        if(fieldDAO.existsById(code)){
            FieldEntity fieldEntity = fieldDAO.getFieldEntityByFieldCode(code);
            return mapping.convertToFieldDTO(fieldEntity);
        }else {
            return new FieldErrorResponse(0, "field not found");
        }
    }

    @Override
    public void deleteField(String code) {
        if (!fieldDAO.existsById(code)) {
            throw new FieldNotFoundException("Cannot delete: field with code " + code + " not found.");
        }
        fieldDAO.deleteById(code);
    }

    @Override
    public void updateField(String code, FieldDTO updatedField) {
        Optional<FieldEntity> tmpField = fieldDAO.findById(code);

        if (tmpField.isEmpty()) {
            throw new StaffNotFoundException("Field not found");
        } else {
            FieldEntity fieldEntity = mapping.convertToFieldEntity(updatedField);
            fieldEntity.setFieldCode(code);
            fieldDAO.save(fieldEntity);
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return mapping.convertFieldToDTOList(fieldDAO.findAll());
    }
}
