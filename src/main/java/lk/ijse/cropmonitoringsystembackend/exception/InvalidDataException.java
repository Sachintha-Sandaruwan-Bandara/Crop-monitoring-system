package lk.ijse.cropmonitoringsystembackend.exception;

public class InvalidDataException  extends RuntimeException{
    public InvalidDataException(String massage){
        super(massage);
    }
}
