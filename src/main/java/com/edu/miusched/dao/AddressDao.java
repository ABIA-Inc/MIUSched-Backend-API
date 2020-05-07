package com.edu.miusched.dao;

import com.edu.miusched.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address ,Long> {
}
