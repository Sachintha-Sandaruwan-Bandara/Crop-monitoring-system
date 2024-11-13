package lk.ijse.cropmonitoringsystembackend.entity;

import jakarta.persistence.*;
import lk.ijse.cropmonitoringsystembackend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "staff")
public class StaffEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String firstName;
    private String lastName;
    private String designation;

    @Enumerated(EnumType.STRING)
    private Enums.Gender gender;

    private LocalDate joinedDate;
    private LocalDate dob;
    @Embedded
    private Address address;

    private String contactNo;
    private String email;

    @Enumerated(EnumType.STRING)
    private Enums.Role role;

}
