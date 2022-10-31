package ru.practicum.explore_with_me.user.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.explore_with_me.user.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.id IN ?1 order by u.id")
    List<User> findAllByIdOrderByIdDesc(List<Long> ids, Pageable pageable);
}
