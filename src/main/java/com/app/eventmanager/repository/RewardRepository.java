package com.app.eventmanager.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.eventmanager.model.Reward;

public interface RewardRepository extends JpaRepository<Reward, Long>{
	@Query("select o from Reward o")
	Set<Reward> findByAll();
}
