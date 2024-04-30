package org.mjulikelion.likelion12thserver.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12thserver.dto.request.user.CreateUserDto;
import org.mjulikelion.likelion12thserver.dto.request.user.UpdateUserDto;
import org.mjulikelion.likelion12thserver.dto.response.ResponseDto;
import org.mjulikelion.likelion12thserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> create(@RequestBody @Valid CreateUserDto createUserDto) {
        userService.create(createUserDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "Created"), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<ResponseDto<Void>> update(@RequestBody @Valid UpdateUserDto updateUserDto,
                                                    @RequestHeader("User-Id") UUID userId) {
        userService.update(updateUserDto, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "Created"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<Void>> delete(@RequestHeader("User-Id") UUID userId) {
        userService.delete(userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "Created"), HttpStatus.CREATED);
    }
}
