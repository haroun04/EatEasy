package com.EatEasy.Services;

import com.EatEasy.Models.FavoriteRestaurant;
import com.EatEasy.Repository.FavoriteRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteRestaurantServiceImpl implements FavoriteRestaurantService {

    private final FavoriteRestaurantRepository favoriteRestaurantRepository;

    @Autowired
    public FavoriteRestaurantServiceImpl(FavoriteRestaurantRepository favoriteRestaurantRepository) {
        this.favoriteRestaurantRepository = favoriteRestaurantRepository;
    }

    @Override
    public List<FavoriteRestaurant> findAll() {
        return favoriteRestaurantRepository.findAll();
    }

    @Override
    public FavoriteRestaurant findById(Long id) {
        return favoriteRestaurantRepository.findById(id).orElseThrow();

    }

    @Override
    public FavoriteRestaurant save(FavoriteRestaurant favoriteRestaurant) {
        return favoriteRestaurantRepository.save(favoriteRestaurant);
    }

    @Override
    public void deleteById(Long id) {
        favoriteRestaurantRepository.deleteById(id);
    }

    @Override
    public FavoriteRestaurant update(Long id, FavoriteRestaurant model) {
        Optional<FavoriteRestaurant> favoriteRestaurantOptional = favoriteRestaurantRepository.findById(id);
        if (favoriteRestaurantOptional.isPresent()) {
            FavoriteRestaurant favoriteRestaurant = favoriteRestaurantOptional.get();
            return favoriteRestaurantRepository.save(favoriteRestaurant);
        }
        return null;
    }
}
