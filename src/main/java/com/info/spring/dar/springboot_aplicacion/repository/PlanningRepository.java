package com.info.spring.dar.springboot_aplicacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/**
 * Repository interface for the Planning entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations:
 * save(), findById(), findAll(), deleteById(), existsById().
 *
 * Spring Data JPA generates the SQL query automatically from the method name.
 * Example: findByUser(user) → SELECT * FROM planning WHERE user_id = ?
 */
public interface PlanningRepository extends JpaRepository<Planning, Long> {

    /**
     * Returns all plannings belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of plannings for that user
     */
    List<Planning> findByUser(User user);
}