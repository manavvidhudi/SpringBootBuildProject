package com.manav.graphql.pack.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manav.graphql.pack.pojo.Vehicle;

@Repository

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}