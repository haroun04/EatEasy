package com.EatEasy.Services;

import com.EatEasy.Models.Restaurant;
import com.EatEasy.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ImageRepository imageRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRestaurantRepository favoriteRestaurantRepository;
    private final BookingRepository bookingRepository;


    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ImageRepository imageRepository, ReviewRepository reviewRepository, FavoriteRestaurantRepository favoriteRestaurantRepository, BookingRepository bookingRepository) {
        this.restaurantRepository = restaurantRepository;
        this.imageRepository = imageRepository;
        this.reviewRepository = reviewRepository;
        this.favoriteRestaurantRepository = favoriteRestaurantRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow();
    }


    @Override
    public Restaurant findByUuid(UUID uuid) {
        return restaurantRepository.findByUuid(uuid);
    }
    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // Eliminar las imágenes asociadas al restaurante
        imageRepository.deleteByRestaurantId(id);
        //Eliminar las reviews
        reviewRepository.deleteByRestaurantId(id);
        //Eliminar los restaurantes relacionados
        favoriteRestaurantRepository.deleteByRestaurantId(id);

        //Eliminar los restaurantes relacionados
        bookingRepository.deleteByRestaurantId(id);
        // Luego eliminar el restaurante
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant update(Long id, Restaurant model) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            restaurant.setName(model.getName());
            restaurant.setLocation(model.getLocation());
            restaurant.setFoodStyle(model.getFoodStyle());
            restaurant.setTimetable(model.getTimetable());
            restaurant.setCapacity(model.getCapacity());
            restaurant.setPhoneNumber(model.getPhoneNumber());
            return restaurantRepository.save(restaurant);
        }
        return null;
    }
    @Override
    public List<Restaurant> findByNameContainingIgnoreCase(String name) {
        return restaurantRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Restaurant patch(Long id, Restaurant restaurant) {
        Restaurant restaurantToPatch = restaurantRepository.findById(id).orElseThrow();

        if (restaurant.getName() != null) {
            restaurantToPatch.setName(restaurant.getName());
        }
        if (restaurant.getUrl() != null) {
            restaurantToPatch.setUrl(restaurant.getUrl());
        }
        if (restaurant.getLocation() != null) {
            restaurantToPatch.setLocation(restaurant.getLocation());
        }
        if (restaurant.getFoodStyle() != null) {
            restaurantToPatch.setFoodStyle(restaurant.getFoodStyle());
        }
        if (restaurant.getTimetable() != null) {
            restaurantToPatch.setTimetable(restaurant.getTimetable());
        }
        if (restaurant.getCapacity() != null) {
            restaurantToPatch.setCapacity(restaurant.getCapacity());
        }
        if (restaurant.getPhoneNumber() != null) {
            restaurantToPatch.setPhoneNumber(restaurant.getPhoneNumber());
        }
        if (restaurant.getDescription() != null) {
            restaurantToPatch.setDescription(restaurant.getDescription());
        }
        if (restaurant.getUserIframeSrc() != null) {
            restaurantToPatch.setUserIframeSrc(restaurant.getUserIframeSrc());
        }

        return restaurantRepository.save(restaurantToPatch);
    }
}
