package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.assets.estate.models.Login;

import java.util.Optional;

public interface Logins extends JpaRepository<Login, Long> {
    @Query("""
        select l from Login l 
         where l.loginType.id = 1 
           and l.email = :email 
           and l.challenge = :password
        """)
    Optional<Login> findByPassword(String email, String password);
}
