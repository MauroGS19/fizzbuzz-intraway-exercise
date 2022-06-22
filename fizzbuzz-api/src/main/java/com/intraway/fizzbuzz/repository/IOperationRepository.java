package com.intraway.fizzbuzz.repository;

import com.intraway.fizzbuzz.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationRepository extends JpaRepository<Operation, Long> {
}
