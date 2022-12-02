package io.barathcr.repository;

import io.barathcr.data.Customer;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Transactional
	@Modifying
	@Query("UPDATE customer SET first_name = :firstName, last_name = :lastName, email = :email, updated_time = :updatedTime WHERE id = :id")
	Integer updateById(String firstName, String lastName, String email, LocalDateTime updatedTime, long id);
}
