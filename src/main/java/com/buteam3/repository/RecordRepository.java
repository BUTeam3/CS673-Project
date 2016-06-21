package com.buteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.buteam3.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

	List<Record> findByState(int state);
}
