package com.qa.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qa.persistence.domain.Livefile;

public interface LivefilesRepository extends JpaRepository<Livefile, Long> {

//	@Query(value = "SELECT * FROM livefile l WHERE l.client_id = :client", nativeQuery = true)
//    List<Livefile> findLivefileByUser(@Param("client") Long client_id);

	List<Livefile> findLivefileByLabel(String label);
}
