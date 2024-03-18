package com.EatEasy.Services;

import com.EatEasy.Models.Image;
import java.util.List;

public interface ImageService {
    List<Image> findAll();

    Image findById(Long id);

    Image save(Image image);

    void deleteById(Long id);

    Image update(Long id, Image image);
}
