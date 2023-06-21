package com.example.eboy_backend_2.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    MessageController(MessageService messageService){this.messageService = messageService;}

    @CrossOrigin(origins = "*")
    @GetMapping("/messages")
    List<Message> getMessages(){ return this.messageService.getMessages(); }

    @CrossOrigin(origins = "*")
    @PostMapping("/messages")
    Message addMessage(@RequestBody Message newMessage){ return this.messageService.addMessage(newMessage); }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/messages/{id}")
    void deleteMessage(@PathVariable Integer id){ this.messageService.deleteMessage(id); }

    @CrossOrigin(origins = "*")
    @GetMapping("/messages/received/{username}")
    List<Message> getMessagesReceived(@PathVariable String username){ return this.messageService.getMessagesReceived(username); }

    @CrossOrigin(origins = "*")
    @PutMapping("/messages/received/{messId}")
    Message updateMessage(@RequestBody Message newMessage, @PathVariable Integer messId){
        return this.messageService.updateMessage(newMessage, messId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/messages/sent/{username}")
    List<Message> getMessagesSent(@PathVariable String username){ return this.messageService.getMessagesSent(username); }

    @CrossOrigin(origins = "*")
    @GetMapping("/messages/unread/{username}")
    Integer getUnreadMessages(@PathVariable String username){ return this.messageService.getUnreadMessages(username); }

    @CrossOrigin(origins = "*")
    @GetMapping("/messages/{sender}/{receiver}")
    List<Message> getMessagesSentBy(@PathVariable String sender, @PathVariable String receiver){ return this.messageService.getMessagesSentBy(sender,receiver); }

}
