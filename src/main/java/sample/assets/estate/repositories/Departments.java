package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.Department;

import java.util.List;

public interface Departments extends JpaRepository<Department, Long> {
    List<Department> findByNameContainsIgnoreCaseOrderByName(String name);
}
