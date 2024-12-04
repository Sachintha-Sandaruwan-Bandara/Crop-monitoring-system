package lk.ijse.cropmonitoringsystembackend.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cropmonitoringsystembackend.config.SecurityConfig;
import lk.ijse.cropmonitoringsystembackend.customObj.UserErrorResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.UserResponse;
import lk.ijse.cropmonitoringsystembackend.dao.UserDAO;
import lk.ijse.cropmonitoringsystembackend.dto.UserDTO;
import lk.ijse.cropmonitoringsystembackend.entity.UserEntity;
import lk.ijse.cropmonitoringsystembackend.exception.DataPersistFailedException;
import lk.ijse.cropmonitoringsystembackend.exception.InvalidStaffDataException;
import lk.ijse.cropmonitoringsystembackend.exception.NotFoundException;
import lk.ijse.cropmonitoringsystembackend.exception.StaffNotFoundException;
import lk.ijse.cropmonitoringsystembackend.service.UserService;
import lk.ijse.cropmonitoringsystembackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final Mapping mapping;


    public UserServiceImpl(Mapping mapping,UserDAO userDAO){
        this.userDAO=userDAO;
        this.mapping=mapping;

    }
    @Override
    public void saveUser(UserDTO userDTO) {
        if (userDTO.getEmail() == null) {
            throw new InvalidStaffDataException(" Email are required.");
        }
        UserEntity savedUser = userDAO.save(mapping.convertToUserEntity(userDTO));
        if(savedUser == null){
            throw new DataPersistFailedException("Cannot save user");
        }
    }

    @Override
    public UserResponse getUserById(String id) {
        if(userDAO.existsById(id)){
           UserEntity  userEntityById = userDAO.getUserEntityById(id);
            return mapping.convertTouserDTO(userEntityById);
        }else {
            return new UserErrorResponse(0, "User not found");
        }
    }

    @Override
    public void deleteUser(String id) {
        if (!userDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete: User with ID " + id + " not found.");
        }
        userDAO.deleteById(id);
    }

    @Override
    public void updateUser(String id, UserDTO updatedUser) {
        Optional<UserEntity> tmpUser = userDAO.findById(id);

        if (tmpUser.isEmpty()) {
            throw new StaffNotFoundException("User not found");
        } else {
           UserEntity userEntity = mapping.convertToUserEntity(updatedUser);
            userEntity.setId(id);
           userDAO.save(userEntity);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return mapping.convertUserToDTOList(userDAO.findAll());
    }


    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userDAO.findByEmail(email)
                        .orElseThrow(()-> new NotFoundException("User Not found"));
    }
}
