package com.EatEasy.Controllers;

import com.EatEasy.Dtos.ImageRequestDto;
import com.EatEasy.Dtos.ImageResponseDto;
import com.EatEasy.Mapper.ImageMapper;
import com.EatEasy.Services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@Slf4j
public class ImageController {
    private final ImageMapper imageMapper;
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageMapper imageMapper, ImageService imageService) {
        this.imageMapper = imageMapper;
        this.imageService = imageService;
    }

    @GetMapping("")
    public ResponseEntity<List<ImageResponseDto>> getAllImages(){
        log.info("getAllImages");
        return ResponseEntity.ok(
                imageMapper.toResponse(imageService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponseDto> getImageByID(@PathVariable Long id){
        log.info("getImageByID");
        return ResponseEntity.ok(
                imageMapper.toResponse(imageService.findById(id))
        );
    }

    @PostMapping("")
    public ResponseEntity<ImageResponseDto> createImage(@RequestBody ImageRequestDto imageRequestDto){
        log.info("createImage");
        ImageResponseDto imageResponseDto = imageMapper.toResponse(imageService.save(imageMapper.toModel(imageRequestDto)));
        return ResponseEntity.ok(imageResponseDto);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id){
        log.info("deleteImage");
        imageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
