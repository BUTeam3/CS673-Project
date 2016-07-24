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
     * Finds 1 Channel in database based on channel name
     * 
     * @param channelname
     * @return Channel found
     */	
	Channel findByChannelname(String channelname); 
    /**
     * Finds 1 Channel in database based on taskid
     * 
     * @param taskid
     * @return Channel found
     */	
	Channel findByTaskid(int taskid);

}
