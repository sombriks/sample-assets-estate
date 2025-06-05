package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.assets.estate.models.Group;
import sample.assets.estate.models.User;

import java.util.Set;

public interface Groups extends JpaRepository<Group, Long> {
    @Query(value = """
                select g.name 
                  from groups  g 
                  join users_groups ug 
                    on g.id = ug.groups_id
                 where ug.users_id = :userId
            """, nativeQuery = true)
    Set<String> listNamesFor(Long userId);
}
