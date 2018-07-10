package com.cg.ssp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ssp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,FriendRepository{

}