package ae.tutorme.dto;


import ae.tutorme.model.Course;
import ae.tutorme.model.Message;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/23/17.
 */

public class MessageDTO implements Serializable {


    private int id;
    private int userId;
    private Set<MessageDTO> messages ;
    private int messageId;
    private int reciverId;
    private String subject;
    private String body;



    public MessageDTO(Message message) {
        this.id = message.getId();
        this.body = message.getBody();
        this.subject = message.getSubject();
        this.reciverId = message.getReciverId();
        this.userId = message.getUser().getUserId();
        this.messageId = message.getMessage().getId();
        this.messages = converter(message.getMessages());
    }

    public Set<MessageDTO> converter(Set<Message> messages) {
        Set<MessageDTO> messageDTOs = null;
        for (Message m : messages) {
            MessageDTO messageDTO = new MessageDTO(m);
            messageDTOs.add(messageDTO);
        }
        return messageDTOs;
    }

    public Set<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageDTO> messages) {
        this.messages = messages;
    }

    public int getMessage() {
        return messageId;
    }

    public void setMessage(int message) {
        this.messageId = message;
    }

    public int getId() {
        return id;
    }

    public int getUser() {
        return userId;
    }

    public void setUser(int user) {
        this.userId = user;
    }

    public int getReciverId() {
        return reciverId;
    }

    public void setReciverId(int reciverId) {
        this.reciverId = reciverId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
