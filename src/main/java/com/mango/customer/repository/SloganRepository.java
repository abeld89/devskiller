package com.mango.customer.repository;

import com.mango.customer.model.Slogan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SloganRepository extends JpaRepository<Slogan, Long>, JpaSpecificationExecutor<Slogan> {

	Long countByUserId(Long userId);
}
