package com.example.eboy_backend_2.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    /* Get messages between 2 users */
    @Query(value="SELECT * FROM eboydb.messages m WHERE m.sender_username= :sender and m.receiver_username= :receiver", nativeQuery=true)
    List<Message> findMessagesSentBy(@Param("sender") String sender, @Param("receiver") String receiver);

    /* Get messages sent by user */
    @Query(value="SELECT * FROM eboydb.messages m WHERE m.sender_username= :sender", nativeQuery=true)
    List<Message> findMessagesSent(@Param("sender") String sender);

    /* Get all messages user has received */
    @Query(value="SELECT * FROM eboydb.messages m WHERE m.receiver_username= :receiver", nativeQuery=true)
    List<Message> findMessagesReceived(@Param("receiver") String receiver);

    /* Get number of unread messages */
    @Query(value="SELECT count(*) FROM eboydb.messages m WHERE m.receiver_username= :receiver and m.is_read=0", nativeQuery=true)
    Integer findUnreadMessages(@Param("receiver") String receiver);
}
