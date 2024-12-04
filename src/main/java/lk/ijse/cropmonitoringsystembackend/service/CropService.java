package lk.ijse.cropmonitoringsystembackend.service;

import lk.ijse.cropmonitoringsystembackend.customObj.CropResponse;
import lk.ijse.cropmonitoringsystembackend.dto.CropDTO;


import java.util.List;

public interface CropService {
    public void saveCrop(CropDTO cropDTO);
    public CropResponse getCropByCode(String code);
    public void deleteCrop(String code);
    public void updateCrop(String code, CropDTO updatedCrop);
    public List<CropDTO> getAllCrops();
}
