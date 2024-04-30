package org.mjulikelion.likelion12thserver.model;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Like {
    private final UUID memoId;
    private final LocalDate createdAt;
    @Setter
    private UUID userId;


    public Like(UUID memoId, UUID userId) {
        this.memoId = memoId;
        this.userId = userId;
        this.createdAt = LocalDate.now();
    }

    public boolean isLikedByUser(User user) {
        return this.userId.equals(user.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Like like = (Like) o;
        return memoId.equals(like.memoId) && userId.equals(like.userId);
    }

    @Override
    public int hashCode() {
        return memoId.hashCode() + userId.hashCode();
    }
}
