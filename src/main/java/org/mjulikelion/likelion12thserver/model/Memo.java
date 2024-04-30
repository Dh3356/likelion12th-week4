package org.mjulikelion.likelion12thserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.LinkedList;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mjulikelion.likelion12thserver.exception.ConflictException;
import org.mjulikelion.likelion12thserver.exception.NotFoundException;

@Getter
@SuperBuilder
public class Memo extends Entity {
    @JsonIgnore
    private final LinkedList<Like> likes = new LinkedList<>();
    @Setter
    private UUID writerId;
    @Setter
    private String title;
    @Setter
    private String content;


    public void like(User user) {
        if (isLikedByUser(user)) {
            throw new ConflictException("이미 좋아요를 누른 메모입니다.");
        }
        this.likes.add(new Like(this.id, user.getId()));
    }

    public void unlike(User user) {
        if (!isLikedByUser(user)) {
            throw new NotFoundException("좋아요를 누르지 않은 메모입니다.");
        }
        this.likes.remove(new Like(this.id, user.getId()));
    }

    private boolean isLikedByUser(User user) {
        return this.likes.stream()
                .anyMatch(like -> like.isLikedByUser(user));
    }
}
