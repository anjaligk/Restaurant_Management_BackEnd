package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.management.entity.TableEntity;

public interface TableRepository extends JpaRepository<TableEntity, Integer> {

}
