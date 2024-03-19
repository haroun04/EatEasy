package com.EatEasy.Services;

import com.EatEasy.Models.Admin;
import com.EatEasy.Models.Booking;
import com.EatEasy.Models.FavoriteRestaurant;
import com.EatEasy.Models.Image;
import com.EatEasy.Models.Owner;
import com.EatEasy.Models.Restaurant;
import com.EatEasy.Models.Review;
import com.EatEasy.Models.User;
import com.EatEasy.Services.AdminService;
import com.EatEasy.Services.BookingService;
import com.EatEasy.Services.FavoriteRestaurantService;
import com.EatEasy.Services.ImageService;
import com.EatEasy.Services.OwnerService;
import com.EatEasy.Services.RestaurantService;
import com.EatEasy.Services.ReviewService;
import com.EatEasy.Services.UserService;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InitialDataCreationService {
    private final AdminService adminService;
    private final BookingService bookingService;
    private final FavoriteRestaurantService favoriteRestaurantService;
    private final ImageService imageService;
    private final OwnerService ownerService;
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final Faker faker = new Faker(new Locale("en-US"));

    public void createFakerAdmin(int number) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            Admin admin = new Admin(
                    null,
                    faker.name().fullName(),
                    faker.internet().password()
            );
            adminService.save(admin);
        }
    }

    public void createFakerBooking(int number) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            Booking booking = new Booking(
                    null,
                    faker.number().randomNumber(),
                    faker.date().future(),
                    faker.number().randomDigit()
            );
            bookingService.save(booking);
        }
    }

    public void createFakerFavoriteRestaurant(int number) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            FavoriteRestaurant favoriteRestaurant = new FavoriteRestaurant(
                    null,
                    faker.food().dish(),
                    faker.food().cuisine(),
                    faker.address().city()
            );
            favoriteRestaurantService.save(favoriteRestaurant);
        }
    }

    public void createFakerImage(int number) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            Image image = new Image(
                    null,
                    faker.internet().image(),
                    faker.lorem().sentence()
            );
            imageService.save(image);
        }
    }

    public void createFakerOwner(int number) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            Owner owner = new Owner(
                    null,
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.internet().password()
            );
            ownerService.save(owner);
        }
    }

    public void createFakerRestaurant(int number) {
        if (number <= 0) return;
        List<Owner> owners = ownerService.findAll();
        for (int i = 0; i < number; i++) {
            Owner owner = owners.get(faker.number().numberBetween(0, owners.size()));
            Restaurant restaurant = new Restaurant(
                    null,
                    UUID.randomUUID(),
                    faker.company().name(),
                    faker.address().city(),
                    faker.food().cuisine(),
                    faker.address().streetAddress(),
                    faker.phoneNumber().cellPhone(),
                    LocalTime.of(faker.number().numberBetween(6, 12), 0),
                    LocalTime.of(faker.number().numberBetween(14, 22), 0),
                    faker.number().numberBetween(10, 200)
            );
            restaurant.setOwner(owner);
            restaurantService.save(restaurant);
        }
    }

    public void createFakerReview(int number) {
        if (number <= 0) return;
        List<User> users = userService.findAll();
        for (int i = 0; i < number; i++) {
            User user = users.get(faker.number().numberBetween(0, users.size()));
            Review review = new Review(
                    null,
                    faker.lorem().sentence(),
                    faker.number().numberBetween(1, 5)
            );
            review.setUser(user);
            reviewService.save(review);
        }
    }

    public void createFakerUser(int number) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            User user = new User(
                    null,
                    faker.name().username(),
                    faker.internet().emailAddress(),
                    faker.internet().password()
            );
            userService.save(user);
        }
    }
}
