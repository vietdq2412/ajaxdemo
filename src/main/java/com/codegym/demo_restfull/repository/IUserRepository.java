package com.codegym.demo_restfull.repository;

import com.codegym.demo_restfull.model.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<AppUser, Long> {

}
