package org.mjulikelion.likelion12thserver.service;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12thserver.dto.request.memo.CreateMemoDto;
import org.mjulikelion.likelion12thserver.dto.request.memo.UpdateMemoDto;
import org.mjulikelion.likelion12thserver.dto.response.memo.LikeListResponseData;
import org.mjulikelion.likelion12thserver.dto.response.memo.MemoListResponseData;
import org.mjulikelion.likelion12thserver.dto.response.memo.MemoResponseData;
import org.mjulikelion.likelion12thserver.exception.ForbiddenException;
import org.mjulikelion.likelion12thserver.exception.NotFoundException;
import org.mjulikelion.likelion12thserver.model.Memo;
import org.mjulikelion.likelion12thserver.model.User;
import org.mjulikelion.likelion12thserver.repository.MemoRepository;
import org.mjulikelion.likelion12thserver.repository.UserRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MemoService {
    private MemoRepository memoRepository;
    private UserRepository userRepository;

    public MemoResponseData find(UUID memoId) {
        MemoResponseData memoResponseData = MemoResponseData.builder()
                .memo(memoRepository.findById(memoId))
                .likeCount(memoRepository.findById(memoId).getLikes().size())
                .build();

        return memoResponseData;
    }

    public MemoListResponseData findAll(UUID userId) {
        this.validateUser(userId);
        List<Memo> memoList = memoRepository.findAllByUserId(userId);
        MemoListResponseData memoListResponseData = MemoListResponseData.builder()
                .memoList(memoList)
                .build();

        return memoListResponseData;
    }

    public void create(CreateMemoDto createMemoDto, UUID userId) {
        this.validateUser(userId);
        UUID memoId = UUID.randomUUID();

        Memo newMemo = Memo.builder()
                .id(memoId)
                .writerId(userId)
                .title(createMemoDto.getTitle())
                .content(createMemoDto.getContent())
                .build();

        memoRepository.save(newMemo);
    }

    public void update(UpdateMemoDto updateMemoDto, UUID memoId, UUID userId) {
        this.validateUser(userId);
        Memo memoToUpdate = memoRepository.findById(memoId);
        this.validateMemoAuthor(memoToUpdate, userId);

        memoToUpdate.setTitle(updateMemoDto.getTitle());
        memoToUpdate.setContent(updateMemoDto.getContent());

        memoRepository.update(memoToUpdate);
    }

    public void delete(UUID memoId, UUID userId) {
        this.validateUser(userId);
        Memo memoToDelete = memoRepository.findById(memoId);
        this.validateMemoAuthor(memoToDelete, userId);

        memoRepository.deleteById(memoId);
    }

    public void like(UUID memoId, UUID userId) {
        User user = userRepository.findById(userId);
        Memo memo = memoRepository.findById(memoId);
        memo.like(user);

        memoRepository.update(memo);
    }

    public void unlike(UUID memoId, UUID userId) {
        User user = userRepository.findById(userId);
        Memo memo = memoRepository.findById(memoId);
        memo.unlike(user);

        memoRepository.update(memo);
    }

    public LikeListResponseData findAllLikes(UUID memoId) {
        Memo memo = memoRepository.findById(memoId);
        LikeListResponseData likeListResponseData = LikeListResponseData.builder()
                .likeList(memo.getLikes())
                .likeCount(memo.getLikes().size())
                .build();
        return likeListResponseData;
    }

    private void validateUser(UUID userId) {
        if (!userRepository.isExistById(userId)) {
            throw new NotFoundException("User 을 찾을 수 없습니다.");
        }
    }

    private void validateMemoAuthor(Memo memo, UUID userId) {
        if (!memo.getWriterId().equals(userId)) {
            throw new ForbiddenException("작성자만 수정할 수 있습니다.");
        }
    }
}
