package lk.ijse.cropmonitoringsystembackend.exception;

import lk.ijse.cropmonitoringsystembackend.customObj.VehicleResponse;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException (String message) {
        super(message);
    }
}
