package com.buteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.buteam3.entity.Channel;

/**
 * Repository which performs various queries for message data in database
 * 
 * @author buteam3
 */
@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    /**
     * Find List of messages in database based on channelID
     * 
     * @param channelid
     * @return List of messages found
     */	
    List<Channel> findBytaskid(int taskid);
	Channel findByChannelname(String channelname);

}
