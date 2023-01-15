package com.scm.controller;

import com.scm.domain.ImageFile;
import com.scm.dto.ImageFileDTO;
import com.scm.dto.response.ImageSavedResponse;
import com.scm.dto.response.ResponseMessage;
import com.scm.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/files")
public class ImageFileController {

    @Autowired
    private ImageFileService imageFileService;

    //http://localhost:8080/files/upload
    @PostMapping("/upload")
    @PreAuthorize("hasRole('RETAILER')")
    public ResponseEntity<ImageSavedResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String imageId = imageFileService.saveImage(file);
        ImageSavedResponse response = new ImageSavedResponse(imageId, ResponseMessage.IMAGE_SAVED_RESPONSE_MESSAGE, true);
        return ResponseEntity.ok(response);
    }

    //http://localhost:8080/files/download/{id}
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        ImageFile imageFile = imageFileService.getImageById(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + imageFile.getName()).
                body(imageFile.getImageData().getData());
    }

    //http://localhost:8080/files/display/{id}
    @GetMapping("/display/{id}")
    public ResponseEntity<byte[]> displayFile(@PathVariable String id) {
        ImageFile imageFile = imageFileService.getImageById(id);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageFile.getImageData().getData(), header, HttpStatus.OK);
    }

    //http://localhost:8080/files
    @GetMapping
    @PreAuthorize("hasRole('RETAILER')")
    public ResponseEntity<List<ImageFileDTO>> getAllImages() {
        List<ImageFileDTO> allImageDTO = imageFileService.getAllImages();
        return ResponseEntity.ok(allImageDTO);
    }
}
