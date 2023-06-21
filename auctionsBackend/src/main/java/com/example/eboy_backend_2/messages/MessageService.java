package com.example.eboy_backend_2.messages;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    List<Message> getMessages(){
        return this.messageRepository.findAll();
    }

    Message addMessage(@RequestBody Message newMessage){
        return this.messageRepository.save(newMessage);
    }

    void deleteMessage(@PathVariable Integer messageId) { this.messageRepository.deleteById(messageId); }

    List<Message> getMessagesSentBy(@PathVariable String sender, @PathVariable String receiver){return this.messageRepository.findMessagesSentBy(sender, receiver);}

    List<Message> getMessagesSent(@PathVariable String sender){ return this.messageRepository.findMessagesSent(sender);}

    List<Message> getMessagesReceived(@PathVariable String receiver){return this.messageRepository.findMessagesReceived(receiver);}

    Message updateMessage(@RequestBody Message newMessage, @PathVariable Integer messId){
        return this.messageRepository.findById(messId)
                .map(message -> {
                    message.setId(newMessage.getId());
                    message.setMessage(newMessage.getMessage());
                    message.setReceiverUsername(newMessage.getReceiverUsername());
                    message.setSenderUsername(newMessage.getSenderUsername());
                    message.setIsRead(true);

                    return messageRepository.save(newMessage);
                })
                .orElseGet(()->{
                    newMessage.setId(messId);
                    return this.messageRepository.save(newMessage);
                });
    }

    Integer getUnreadMessages(@PathVariable String receiver){return this.messageRepository.findUnreadMessages(receiver);}
}
