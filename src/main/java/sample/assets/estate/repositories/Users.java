package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.assets.estate.models.User;

import java.util.List;
import java.util.Optional;

public interface Users extends JpaRepository<User, Long> {
    List<User> findByNameContainsIgnoreCaseOrderByName(String name);

    @Query("""
                select u 
                  from User u
            join fetch u.groups
            join fetch u.logins
                 where u in (select l.user 
                               from Login l
                              where l.email = :email)  
            """)
    Optional<User> findByEmail(String email);
}
