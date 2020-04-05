package com.log.book.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.log.book.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long	> {
	UserEntity findByEmail(String email);
	List<UserEntity> findAll();

}
