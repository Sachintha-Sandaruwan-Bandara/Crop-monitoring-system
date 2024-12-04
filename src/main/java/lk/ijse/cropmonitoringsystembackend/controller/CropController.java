package lk.ijse.cropmonitoringsystembackend.controller;

import lk.ijse.cropmonitoringsystembackend.customObj.CropResponse;
import lk.ijse.cropmonitoringsystembackend.dto.CropDTO;
import lk.ijse.cropmonitoringsystembackend.exception.InvalidStaffDataException;
import lk.ijse.cropmonitoringsystembackend.exception.StaffNotFoundException;
import lk.ijse.cropmonitoringsystembackend.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crop")
public class CropController {
    private final CropService cropService;

    @Autowired
    public CropController(CropService cropService){
        this.cropService=cropService;
    }

    @PostMapping
    public ResponseEntity<String> saveCrop(@RequestBody CropDTO cropDTO) {
        try {
            cropService.saveCrop(cropDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Crop saved successfully");
        } catch (InvalidStaffDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save crop");
        }
    }

    // Get a staff member by ID
    @GetMapping("/{code}")
    public ResponseEntity<CropResponse> getCropByCode(@PathVariable String code) {
        try {
            CropResponse cropByCode=cropService.getCropByCode(code);
            return ResponseEntity.ok(cropByCode);
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get all staff members
    @GetMapping
    public ResponseEntity<List<CropDTO>> getAllCrops() {
        List<CropDTO> allCrops = cropService.getAllCrops();
        return ResponseEntity.ok(allCrops);
    }
    @PreAuthorize("!hasRole('ADMINISTRATIVE')")
    // Update a staff member
    @PutMapping("/{code}")
    public ResponseEntity<String> updateCrop(@PathVariable String code, @RequestBody CropDTO cropDTO) {
        try {
            cropService.updateCrop(code,cropDTO);
            return ResponseEntity.ok("Crop updated successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update crop");
        }
    }

    // Delete a staff member
    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteField(@PathVariable String code) {
        try {
            cropService.deleteCrop(code);
            return ResponseEntity.ok("crop deleted successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete crop");
        }
    }
}
