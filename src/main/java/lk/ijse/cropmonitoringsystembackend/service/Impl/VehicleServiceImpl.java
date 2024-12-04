package lk.ijse.cropmonitoringsystembackend.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cropmonitoringsystembackend.customObj.VehicleErrorResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.VehicleResponse;
import lk.ijse.cropmonitoringsystembackend.dao.VehicleDAO;
import lk.ijse.cropmonitoringsystembackend.dto.VehicleDTO;
import lk.ijse.cropmonitoringsystembackend.entity.VehicleEntity;
import lk.ijse.cropmonitoringsystembackend.exception.DataPersistFailedException;
import lk.ijse.cropmonitoringsystembackend.service.VehicleService;
import lk.ijse.cropmonitoringsystembackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    private final VehicleDAO vehicleDAO;
    private final Mapping mapping;

    @Autowired
    public VehicleServiceImpl(VehicleDAO vehicleDAO,Mapping mapping){
        this.mapping=mapping;
        this.vehicleDAO=vehicleDAO;
    }

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        if (vehicleDTO.getLicensePlateNumber() == null) {
            throw new DataPersistFailedException(" License plate number required.");
        }
        VehicleEntity vehicleEntity= vehicleDAO.save(mapping.convertToVehicleEntity(vehicleDTO));
        if(vehicleEntity == null){
            throw new DataPersistFailedException("Cannot save vehicle");
        }
    }

    @Override
    public VehicleResponse getVehicleByCode(String code) {
        if(vehicleDAO.existsById(code)){
            VehicleEntity vehicleEntity = vehicleDAO.getVehicleEntityByVehicleCode(code);
            return mapping.convertToVehicleDTO(vehicleEntity);
        }else {
            return new VehicleErrorResponse(0, "vehicle not found");
        }
    }

    @Override
    public void deleteVehicle(String code) {
        if (!vehicleDAO.existsById(code)) {
            throw new DataPersistFailedException("Cannot delete: Vehicle with code " + code + " not found.");
        }
        vehicleDAO.deleteById(code);
    }

    @Override
    public void updateVehicle(String code, VehicleDTO updatedVehicle) {
        Optional<VehicleEntity> tmpVehicle = vehicleDAO.findById(code);

        if (tmpVehicle.isEmpty()) {
            throw new DataPersistFailedException("Vehicle not found");
        } else {
            VehicleEntity vehicleEntity = mapping.convertToVehicleEntity(updatedVehicle);
            vehicleEntity.setVehicleCode(code);
            vehicleDAO.save(vehicleEntity);
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapping.convertVehicleToDTOList(vehicleDAO.findAll());
    }
}
