package com.transformr.unit.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transformr.unit.models.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, UUID> {

}
