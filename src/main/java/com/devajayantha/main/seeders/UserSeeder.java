package com.devajayantha.main.seeders;

import com.devajayantha.main.models.entities.User;
import com.devajayantha.main.models.enums.RoleType;
import com.devajayantha.main.models.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {
    private final UserRepository repository;

    public UserSeeder(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            User entity = new User();
            entity.setName("Admin");
            entity.setEmail("admin@gmail.com");
            entity.setRole(RoleType.ADMIN);
            entity.setPassword("password");

            repository.saveAndFlush(entity);
        }
    }
}
