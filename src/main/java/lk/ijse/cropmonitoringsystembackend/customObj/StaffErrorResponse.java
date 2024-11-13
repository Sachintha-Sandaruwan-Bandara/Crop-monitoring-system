package lk.ijse.cropmonitoringsystembackend.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffErrorResponse implements StaffResponse {
    private int errorCode;
    private String errorMessage;
}