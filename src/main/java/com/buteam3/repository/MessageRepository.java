package com.buteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.buteam3.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findBychannelid(int channelid);
}
