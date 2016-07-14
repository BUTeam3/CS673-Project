package com.buteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.buteam3.entity.Record;

/**
 * Repository which performs various queries for Record data in database
 * 
 * @author buteam3
 */

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
     /**
     * Find List of Records in database based on passed state value
     * 
     * @param state
     * @return List of Records found
     */	
        List<Record> findByState(int state);		
		Record findById(long id);
}
