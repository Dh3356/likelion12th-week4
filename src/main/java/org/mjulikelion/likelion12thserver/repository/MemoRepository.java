package org.mjulikelion.likelion12thserver.repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;
import org.mjulikelion.likelion12thserver.model.Memo;
import org.springframework.stereotype.Repository;

@Repository
public class MemoRepository extends EntityRepository<Memo> {
    public MemoRepository() {
        super(new LinkedHashSet<>(), Memo.class);
    }

    public List<Memo> findAllByUserId(final UUID userId) {
        return data.stream()
                .filter(memo -> memo.getWriterId().equals(userId))
                .toList();
    }
}
