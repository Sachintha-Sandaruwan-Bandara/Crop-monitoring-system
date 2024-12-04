package lk.ijse.cropmonitoringsystembackend.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cropmonitoringsystembackend.customObj.CropErrorResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.CropResponse;
import lk.ijse.cropmonitoringsystembackend.dao.CropDAO;
import lk.ijse.cropmonitoringsystembackend.dto.CropDTO;
import lk.ijse.cropmonitoringsystembackend.entity.CropEntity;
import lk.ijse.cropmonitoringsystembackend.exception.*;
import lk.ijse.cropmonitoringsystembackend.service.CropService;
import lk.ijse.cropmonitoringsystembackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    private final CropDAO cropDAO;
    private final Mapping mapping;

    @Autowired
    public CropServiceImpl(Mapping mapping,CropDAO cropDAO){
        this.cropDAO=cropDAO;
        this.mapping=mapping;
    }
    @Override
    public void saveCrop(CropDTO cropDTO) {
        if (cropDTO.getCommonName() == null) {
            throw new InvalidFieldDataException(" Crop Name are required.");
        }
        CropEntity cropEntity = cropDAO.save(mapping.convertToCropEntity(cropDTO));
        if(cropEntity == null){
            throw new DataPersistFailedException("Cannot save crop");
        }
    }

    @Override
    public CropResponse getCropByCode(String code) {
        if(cropDAO.existsById(code)){
            CropEntity cropEntity = cropDAO.getCropEntityByCropCode(code);
            return mapping.convertToCropDTO(cropEntity);
        }else {
            return new CropErrorResponse(0, "Crop not found");
        }
    }

    @Override
    public void deleteCrop(String code) {
        if (!cropDAO.existsById(code)) {
            throw new DataPersistFailedException("Cannot delete: Crop with code " + code + " not found.");
        }
        cropDAO.deleteById(code);
    }

    @Override
    public void updateCrop(String code, CropDTO updatedCrop) {
        Optional<CropEntity> tmpCrop = cropDAO.findById(code);

        if (tmpCrop.isEmpty()) {
            throw new NotFoundException("Crop not found");
        } else {
           CropEntity cropEntity = mapping.convertToCropEntity(updatedCrop);
            cropEntity.setCropCode(code);
            cropDAO.save(cropEntity);
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return mapping.convertCropToDTOList(cropDAO.findAll());
    }
}
