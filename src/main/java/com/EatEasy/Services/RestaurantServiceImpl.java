package com.EatEasy.Services;

import com.EatEasy.Models.Restaurant;
import com.EatEasy.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
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
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteById(Long id) {
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
}
