package org.mjulikelion.likelion12thserver.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thserver.dto.request.user.CreateUserDto;
import org.mjulikelion.likelion12thserver.dto.request.user.UpdateUserDto;
import org.mjulikelion.likelion12thserver.model.User;
import org.mjulikelion.likelion12thserver.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public void create(CreateUserDto createUserDto) {
        UUID userId = UUID.randomUUID();
        User newUser = User.builder()
                .id(userId)
                .name(createUserDto.getName())
                .build();

        log.info("User ID: {}", userId);
        userRepository.save(newUser);
    }

    public void update(UpdateUserDto updateUserDto, UUID userId) {
        User userToUpdate = userRepository.findById(userId);
        userToUpdate.setName(updateUserDto.getName());

        userRepository.update(userToUpdate);
    }

    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }
}
