package org.mjulikelion.likelion12thserver.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12thserver.dto.request.memo.CreateMemoDto;
import org.mjulikelion.likelion12thserver.dto.request.memo.UpdateMemoDto;
import org.mjulikelion.likelion12thserver.dto.response.ResponseDto;
import org.mjulikelion.likelion12thserver.dto.response.memo.LikeListResponseData;
import org.mjulikelion.likelion12thserver.dto.response.memo.MemoListResponseData;
import org.mjulikelion.likelion12thserver.dto.response.memo.MemoResponseData;
import org.mjulikelion.likelion12thserver.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/memos")
public class MemoController {
    private final MemoService memoService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<MemoResponseData>> find(@PathVariable("id") UUID id) {
        MemoResponseData memoResponseData = memoService.find(id);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK", memoResponseData), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<MemoListResponseData>> findAll(@RequestHeader("User-Id") UUID userId) {
        MemoListResponseData memoListResponseData = memoService.findAll(userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK", memoListResponseData), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> create(@RequestBody @Valid CreateMemoDto createMemoDto,
                                                    @RequestHeader("User-Id") UUID userId) {
        memoService.create(createMemoDto, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "Created"), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> update(@RequestBody @Valid UpdateMemoDto updateMemoDto,
                                                    @PathVariable("id") UUID id,
                                                    @RequestHeader("User-Id") UUID userId) {
        memoService.update(updateMemoDto, id, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> delete(@PathVariable("id") UUID id,
                                                    @RequestHeader("User-Id") UUID userId) {
        memoService.delete(id, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK"), HttpStatus.OK);
    }

    @PatchMapping("/{id}/likes")
    public ResponseEntity<ResponseDto<Void>> like(@PathVariable("id") UUID id, @RequestHeader("User-Id") UUID userId) {
        memoService.like(id, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/likes")
    public ResponseEntity<ResponseDto<Void>> unlike(@PathVariable("id") UUID id,
                                                    @RequestHeader("User-Id") UUID userId) {
        memoService.unlike(id, userId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK"), HttpStatus.OK);
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<ResponseDto<LikeListResponseData>> getLikes(@PathVariable("id") UUID id) {
        LikeListResponseData likeListResponseData = memoService.findAllLikes(id);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK", likeListResponseData), HttpStatus.OK);
    }
}
