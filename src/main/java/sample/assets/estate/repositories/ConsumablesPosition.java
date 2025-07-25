package sample.assets.estate.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.assets.estate.models.ConsumablePosition;
import sample.assets.estate.models.User;

import java.util.List;

public interface ConsumablesPosition extends JpaRepository<ConsumablePosition, Long> {
    @Query("""
        select cp 
          from ConsumablePosition  cp
         where cp.id in (select max (cp.id) 
                           from ConsumablePosition  cp
                          where cp.asset.type.id = 1
                            and lower(cp.asset.name) like '%'||lower(:q)||'%'
                             or lower(cp.department.name) like '%'||lower(:q)||'%'
                       group by cp.asset.id) 
    """)
    List<ConsumablePosition> findLatestPosition(String q, User user, Sort sort);
}
