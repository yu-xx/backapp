package com.knowworld.backapp.model.dao;

import com.knowworld.backapp.model.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends PagingAndSortingRepository<Role, Long> {

    List<Role> findByStatus(boolean b);
}
