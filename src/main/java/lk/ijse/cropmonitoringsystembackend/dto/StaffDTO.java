package lk.ijse.cropmonitoringsystembackend.dto;

import lk.ijse.cropmonitoringsystembackend.customObj.StaffResponse;
import lk.ijse.cropmonitoringsystembackend.entity.Address;
import lk.ijse.cropmonitoringsystembackend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO,StaffResponse{
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private Enums.Gender gender;
    private LocalDate joinedDate;
    private LocalDate dob;
    private Address address;
    private String contactNo;
    private String email;
    private Enums.Role role;
}
