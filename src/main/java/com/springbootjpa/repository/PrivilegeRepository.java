package com.springbootjpa.repository;

import com.springbootjpa.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PrivilegeRepository")
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
