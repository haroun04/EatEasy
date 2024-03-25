package com.EatEasy.Services;

import com.EatEasy.Models.Admin;
import com.EatEasy.Models.Booking;
import com.EatEasy.Models.FavoriteRestaurant;
import com.EatEasy.Models.Image;
import com.EatEasy.Models.Owner;
import com.EatEasy.Models.Restaurant;
import com.EatEasy.Models.Review;
import com.EatEasy.Models.User;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
                    UUID.randomUUID(),
                    faker.name().firstName(),
                    faker.internet().password()
            );
            adminService.save(admin);
        }
    }

    public void createFakerBooking(int number) {
        if (number <= 0) return;
        Faker faker = new Faker();

        List<User> users = userService.findAll();
        List<Restaurant> restaurants = restaurantService.findAll();

        for (int i = 0; i < number; i++) {
            int userIndex = faker.number().numberBetween(0, users.size());
            int restaurantIndex = faker.number().numberBetween(0, restaurants.size());
            int numberDiners = faker.number().numberBetween(1, 10);

            User user = users.get(userIndex);
            Restaurant restaurant = restaurants.get(restaurantIndex);

            Booking booking = new Booking();
            booking.setUuid(UUID.randomUUID());
            booking.setNumberDiners(numberDiners);
            booking.setCreatedAt(LocalDateTime.now());
            booking.setReservedAt(generateRandomReservedAt());
            booking.setUser(user);
            booking.setRestaurant(restaurant);

            bookingService.save(booking);
        }
    }

    public LocalDateTime generateRandomReservedAt() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        LocalDateTime maxDateTime = now.plusMonths(1);

        long minMillis = now.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
        long maxMillis = maxDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
        long randomMillis = ThreadLocalRandom.current().nextLong(minMillis, maxMillis);

        return LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(randomMillis), java.time.ZoneId.systemDefault());
    }

    public void createFakerFavoriteRestaurant(int number) {
        if (number <= 0) return;
        Faker faker = new Faker();

        List<User> users = userService.findAll();
        List<Restaurant> restaurants = restaurantService.findAll();

        for (int i = 0; i < number; i++) {
            int userIndex = faker.number().numberBetween(0, users.size());
            int restaurantIndex = faker.number().numberBetween(0, restaurants.size());

            User user = users.get(userIndex);
            Restaurant restaurant = restaurants.get(restaurantIndex);

            FavoriteRestaurant favoriteRestaurant = new FavoriteRestaurant();
            favoriteRestaurant.setUser(user);
            favoriteRestaurant.setRestaurant(restaurant);

            favoriteRestaurantService.save(favoriteRestaurant);
        }
    }

    public void createFakerImage(int number) {
        if (number <= 0) return;
        Faker faker = new Faker();

        List<Restaurant> restaurants = restaurantService.findAll();

        for (int i = 0; i < number; i++) {
            int restaurantIndex = faker.number().numberBetween(0, restaurants.size());
            String imageUrl = faker.internet().url();

            Restaurant restaurant = restaurants.get(restaurantIndex);

            Image image = new Image();
            image.setUrl(imageUrl);
            image.setRestaurant(restaurant);

            imageService.save(image);
        }
    }

    public void createFakerOwner(int number) {
        if (number <= 0) return;
        Faker faker = new Faker();

        for (int i = 0; i < number; i++) {
            Owner owner = new Owner();
            owner.setUuid(UUID.randomUUID());
            owner.setName(faker.name().fullName());
            owner.setEmail(faker.internet().emailAddress());
            owner.setPassword(faker.internet().password());

            ownerService.save(owner);
        }
    }


    public void createFakerRestaurant(int number) {
        if (number <= 0) return;
        Faker faker = new Faker();
        List<Owner> owners = ownerService.findAll();

        for (int i = 0; i < number; i++) {
            int ownerIndex = faker.number().numberBetween(0, owners.size());
            Owner owner = owners.get(ownerIndex);

            Restaurant restaurant = new Restaurant();
            restaurant.setUuid(UUID.randomUUID());
            restaurant.setName(faker.company().name());
            restaurant.setLocation(faker.address().fullAddress());
            restaurant.setFoodStyle(cuisine());
            restaurant.setTimetable(generateTimetable());
            restaurant.setCapacity(faker.number().numberBetween(10, 100));
            restaurant.setPhoneNumber(faker.phoneNumber().phoneNumber());
            restaurant.setOwner(owner);

            restaurantService.save(restaurant);
        }
    }

    public static String cuisine() {
        String[] cuisines = {
                "Italiana", "Mexicana", "Asiática", "Mediterránea", "Americana", "Vegetariana", "Vegana", "Griega"
        };
        int index = ThreadLocalRandom.current().nextInt(cuisines.length);
        return cuisines[index];
    }

    public static String generateTimetable() {
        int openingHour = ThreadLocalRandom.current().nextInt(6, 10); // Abre entre las 6 AM y las 9 AM
        int closingHour = ThreadLocalRandom.current().nextInt(18, 23); // Cierra entre las 6 PM y las 10 PM

        String timetable = String.format("%02d:00 AM - %02d:00 PM", openingHour, closingHour);
        return timetable;
    }

    public void createFakerReview(int number) {
        if (number <= 0) return;
        Faker faker = new Faker();
        List<User> users = userService.findAll();
        List<Restaurant> restaurants = restaurantService.findAll();
        List<Owner> owners = ownerService.findAll();

        for (int i = 0; i < number; i++) {
            int userIndex = faker.number().numberBetween(0, users.size());
            int restaurantIndex = faker.number().numberBetween(0, restaurants.size());
            int ownerIndex = faker.number().numberBetween(0, owners.size());
            User user = users.get(userIndex);
            Restaurant restaurant = restaurants.get(restaurantIndex);
            Owner owner = owners.get(ownerIndex);

            Review review = new Review();
            review.setUuid(UUID.randomUUID());
            review.setComment(faker.lorem().sentence());
            review.setAssessment(faker.number().numberBetween(1, 5));
            review.setCreatedAt(LocalDateTime.now());
            review.setUser(user);
            review.setRestaurant(restaurant);
            review.setOwner(owner);

            reviewService.save(review);
        }
    }


    public void createFakerUser(int number) {
        if (number <= 0) return;
        Faker faker = new Faker();

        for (int i = 0; i < number; i++) {
            User user = new User();
            user.setUuid(UUID.randomUUID());
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());

            userService.save(user);
        }
    }

}
