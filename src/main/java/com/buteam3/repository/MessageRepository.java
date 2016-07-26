package com.buteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.buteam3.entity.Message;

/**
 * Repository which performs various queries for message data in database
 * 
 * @author buteam3
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    /**
     * Find List of messages in database based on channelID
     * 
     * @param channelid
     * @return List of messages found
     */	
    List<Message> findBychannelid(int channelid);
    /**
     * Find List of messages in database based on values greater than MId
     * 
     * @param channelid
     * @return List of messages found
     */	
    List<Message> findByMidGreaterThan(long mid);

}
