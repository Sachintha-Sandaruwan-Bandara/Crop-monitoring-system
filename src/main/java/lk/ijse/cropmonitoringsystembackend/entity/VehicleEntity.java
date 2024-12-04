package lk.ijse.cropmonitoringsystembackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleEntity {

    @Id
    private String vehicleCode;

    private String licensePlateNumber;
    private String category;
    private String fuelType;
    private String status;
    private String remark;
}
