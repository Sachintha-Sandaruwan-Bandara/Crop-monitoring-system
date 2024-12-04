package lk.ijse.cropmonitoringsystembackend.service;


import lk.ijse.cropmonitoringsystembackend.dto.UserDTO;
import lk.ijse.cropmonitoringsystembackend.jwtmodels.JwtAuthResponse;
import lk.ijse.cropmonitoringsystembackend.jwtmodels.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDTO signUp);
    JwtAuthResponse refreshToken(String accessToken);
}
