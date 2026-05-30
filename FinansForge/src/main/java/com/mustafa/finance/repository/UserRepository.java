package com.mustafa.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mustafa.finance.model.User;

public interface  UserRepository extends JpaRepository<User, Long> {

	
}
