package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.Entity.RentAreaEntity;

public interface RentAreaRepository {
    List<RentAreaEntity> getValuebyBuilding(Long id);
}
