package com.mindblower.friends.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindblower.friends.entities.Address;

public interface AddressRepo extends JpaRepository<Address,Integer>{

}
