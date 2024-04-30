package org.mjulikelion.likelion12thserver.repository;

import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12thserver.exception.ConflictException;
import org.mjulikelion.likelion12thserver.exception.NotFoundException;
import org.mjulikelion.likelion12thserver.model.Entity;

@AllArgsConstructor
public abstract class EntityRepository<T extends Entity> {
    protected final Collection<T> data;
    protected final Class<T> entityClass;

    public T findById(final UUID id) {
        return data.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(this.entityClass.getSimpleName() + "을 찾을 수 없습니다."));
    }

    public void save(final T entity) {
        if (isExistById(entity.getId())) {
            throw new ConflictException(this.entityClass.getSimpleName() + "이 이미 존재합니다.");
        }
        data.add(entity);
    }

    public void update(final T entity) {
        if (!isExistById(entity.getId())) {
            throw new NotFoundException(this.entityClass.getSimpleName() + "을 찾을 수 없습니다.");
        }
        data.remove(entity);
        data.add(entity);
    }

    public void deleteById(final UUID id) {
        if (!isExistById(id)) {
            throw new NotFoundException(this.entityClass.getSimpleName() + "을 찾을 수 없습니다.");
        }
        data.removeIf(d -> d.getId().equals(id));
    }

    public boolean isExistById(final UUID id) {
        return data.stream()
                .anyMatch(d -> d.getId().equals(id));
    }
}
