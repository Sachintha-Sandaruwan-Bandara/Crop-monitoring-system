package lk.ijse.cropmonitoringsystembackend.service;

import lk.ijse.cropmonitoringsystembackend.customObj.StaffResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.UserResponse;
import lk.ijse.cropmonitoringsystembackend.dto.StaffDTO;
import lk.ijse.cropmonitoringsystembackend.dto.UserDTO;
import lk.ijse.cropmonitoringsystembackend.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    public void saveUser(UserDTO userDTO);
    public UserResponse getUserById(String id);
    public void deleteUser(String id);
    public void updateUser(String id, UserDTO updatedUser);
    public List<UserDTO> getAllUsers();
    UserDetailsService userDetailsService();
}
