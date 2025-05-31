package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.Department;

public interface Departments extends JpaRepository<Department, Long> {
}
