package com.devajayantha.main.services;

import com.devajayantha.main.models.dtos.UserDto;
import com.devajayantha.main.models.entities.User;
import com.devajayantha.main.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public User createUser(UserDto userDto) {
        boolean userExists = userRepository.findByEmail(userDto.getEmail()).isPresent();

        if (userExists) throw new IllegalArgumentException("User with email: " + userDto.getEmail() + " already exists");

        String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());

        User user = new User(userDto.getName(), userDto.getEmail(), encodedPassword, userDto.getRole());

        return userRepository.saveAndFlush(user);
    }
}
