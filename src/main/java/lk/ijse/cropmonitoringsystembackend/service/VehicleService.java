package lk.ijse.cropmonitoringsystembackend.service;

import lk.ijse.cropmonitoringsystembackend.customObj.FieldResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.VehicleResponse;
import lk.ijse.cropmonitoringsystembackend.dto.FieldDTO;
import lk.ijse.cropmonitoringsystembackend.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    public void saveVehicle(VehicleDTO vehicleDTO);
    public VehicleResponse getVehicleByCode(String code);
    public void deleteVehicle(String code);
    public void updateVehicle(String code,VehicleDTO updatedVehicle);
    public List<VehicleDTO> getAllVehicles();
}
