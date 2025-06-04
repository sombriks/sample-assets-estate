package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.assets.estate.models.Login;

public interface Logins extends JpaRepository<Login, Long> {
    @Query("""
        select l from Login l 
         where l.loginType.id = 1 
           and l.email = :email 
           and l.challenge = :password
        """)
    Login findByPassword(String email, String password);
}
