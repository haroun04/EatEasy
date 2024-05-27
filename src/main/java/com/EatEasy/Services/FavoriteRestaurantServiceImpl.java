package com.EatEasy.Services;

import com.EatEasy.Dtos.UserDto.UserRequestDto;
import com.EatEasy.Mapper.UserMapper;
import com.EatEasy.Mapper.RestaurantMapper;
import com.EatEasy.Models.FavoriteRestaurant;
import com.EatEasy.Models.user.User;
import com.EatEasy.Repository.FavoriteRestaurantRepository;
import com.EatEasy.Repository.UserDetailsRepository;
import com.EatEasy.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteRestaurantServiceImpl implements FavoriteRestaurantService {

    private final FavoriteRestaurantRepository favoriteRestaurantRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final JWTService jwtService;
    @Autowired
    public FavoriteRestaurantServiceImpl(FavoriteRestaurantRepository favoriteRestaurantRepository ,UserDetailsRepository userDetailsRepository,JWTService jwtService) {
        this.favoriteRestaurantRepository = favoriteRestaurantRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.jwtService = jwtService;
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






    public List<FavoriteRestaurant> getFavoriteRestaurantsByUserId(Long userId) {
        return favoriteRestaurantRepository.findByUser_Id(userId);
    }

    public Long getUserIdByEmail(String email) {
        User user = userDetailsRepository.findByEmail(email);
        if (user == null) {
            throw new ExpressionException("User not found");
        }
        return user.getId();
    }
    @Override
    public FavoriteRestaurant updateFavoriteRestaurant(String token, Long id, Boolean liked) {
        // Obtener el nombre de usuario a partir del token
        String email = jwtService.getUsernameFromToken(token);

        // Obtener el ID del usuario a partir del correo electrónico
        Long userId = getUserIdByEmail(email);

        // Buscar el restaurante favorito por su ID
        FavoriteRestaurant favoriteRestaurantToUpdate = favoriteRestaurantRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("FavoriteRestaurant not found"));

        // Verificar si el restaurante favorito pertenece al usuario actual
        if (!favoriteRestaurantToUpdate.getUser().getId().equals(userId)) {
            throw new ExpressionException("User not authorized to update this favorite restaurant");
        }

        // Actualizar el estado de "liked" del restaurante favorito
        favoriteRestaurantToUpdate.setLiked(liked);

        // Guardar los cambios en la base de datos y devolver el restaurante favorito actualizado
        return favoriteRestaurantRepository.save(favoriteRestaurantToUpdate);
    }

}
