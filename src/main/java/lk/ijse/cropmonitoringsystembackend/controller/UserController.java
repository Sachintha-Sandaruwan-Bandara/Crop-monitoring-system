package lk.ijse.cropmonitoringsystembackend.controller;

import lk.ijse.cropmonitoringsystembackend.customObj.UserResponse;
import lk.ijse.cropmonitoringsystembackend.dto.UserDTO;
import lk.ijse.cropmonitoringsystembackend.exception.StaffNotFoundException;
import lk.ijse.cropmonitoringsystembackend.service.AuthenticationService;
import lk.ijse.cropmonitoringsystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService,AuthenticationService authenticationService){
        this.userService=userService;
        this.authenticationService=authenticationService;
    }
//    @PostMapping
//    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
//        try {
//            userService.saveUser(userDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body("user saved successfully");
//        } catch (InvalidStaffDataException ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save user");
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        try {

            UserResponse userById = userService.getUserById(id);
            return ResponseEntity.ok(userById);
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserDTO updatedUser) {
        try {
            userService.updateUser(id,updatedUser);
            return ResponseEntity.ok("User updated successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
        }
    }
}
