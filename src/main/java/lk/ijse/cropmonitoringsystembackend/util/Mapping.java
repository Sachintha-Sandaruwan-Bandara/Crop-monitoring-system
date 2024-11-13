package lk.ijse.cropmonitoringsystembackend.util;

import lk.ijse.cropmonitoringsystembackend.dto.StaffDTO;
import lk.ijse.cropmonitoringsystembackend.entity.StaffEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public Mapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }


    public StaffEntity convertToStaffEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public StaffDTO convertToStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }

    public List<StaffDTO> convertPassengerToDTOList(List<StaffEntity> staffEntities) {
        return modelMapper.map(staffEntities, new TypeToken<List<StaffDTO>>() {
        }.getType());
    }

}

