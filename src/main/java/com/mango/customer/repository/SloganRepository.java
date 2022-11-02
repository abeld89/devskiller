package com.mango.customer.repository;

import com.mango.customer.model.Slogan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SloganRepository extends JpaRepository<Slogan, Long>, JpaSpecificationExecutor<Slogan> {

	int countByUserId(Long userId);

	@Query("select s from Slogan s where s.user.id = ?1")
	List<Slogan> findByUserId(Long userId);
}
