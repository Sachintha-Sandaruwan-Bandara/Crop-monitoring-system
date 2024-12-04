package lk.ijse.cropmonitoringsystembackend.dto;

import lk.ijse.cropmonitoringsystembackend.customObj.EquipmentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentResponse {
    private String equipmentId;
    private String name;
    private String type;
    private String status;
}
