package com.springbootjpa.repository;

import com.springbootjpa.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /** Example of native query
        @Query(value = """
           SELECT * FROM Address
           WHERE country @> '[{"name": "India"}]'
        """, nativeQuery = true)
        List<Address> findAllContainingIndia();
     */
}