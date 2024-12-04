package lk.ijse.cropmonitoringsystembackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentEntity {
    @Id
    private String equipmentId;
    private String name;
    private String type;
    private String status;
}
