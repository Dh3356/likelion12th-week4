package org.mjulikelion.likelion12thserver.repository;

import java.util.HashSet;
import org.mjulikelion.likelion12thserver.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends EntityRepository<User> {
    public UserRepository() {
        super(new HashSet<>(), User.class);
    }
}
