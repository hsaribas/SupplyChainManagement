package com.scm.service;

import com.scm.domain.ImageData;
import com.scm.domain.ImageFile;
import com.scm.dto.ImageFileDTO;
import com.scm.exception.ResourceNotFoundException;
import com.scm.exception.message.ErrorMessage;
import com.scm.repository.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ImageFileService {

    @Autowired
    private ImageFileRepository imageFileRepository;

    public String saveImage(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        ImageFile imageFile = null;

        try {
            ImageData imData = new ImageData(file.getBytes());
            imageFile = new ImageFile(fileName, file.getContentType(), imData);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        imageFileRepository.save(imageFile);
        return imageFile.getId();
    }

    public ImageFile getImageById(String imageId) {
        ImageFile imageFile = imageFileRepository.findById(imageId).orElseThrow(() -> new
                ResourceNotFoundException(String.format(ErrorMessage.IMAGE_NOT_FOUND_MESSAGE, imageId)));
        return imageFile;
    }

    public List<ImageFileDTO> getAllImages() {
        List<ImageFile> imageFiles = imageFileRepository.findAll();

        List<ImageFileDTO> imageFileDTOs = imageFiles.stream().map(imFile -> {
            String imageUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/").
                    path(imFile.getId()).toUriString();
            return new ImageFileDTO(imFile.getName(), imageUri, imFile.getType(), imFile.getLength());
        }).collect(Collectors.toList());
        return imageFileDTOs;
    }

    public void removeById(String id) {
        ImageFile imFile = getImageById(id);
        imageFileRepository.delete(imFile);
    }

    public ImageFile findImageById(String id) {
        return imageFileRepository.findImageById(id).orElseThrow(() -> new
                ResourceNotFoundException(String.format(ErrorMessage.IMAGE_NOT_FOUND_MESSAGE, id)));
    }
}
