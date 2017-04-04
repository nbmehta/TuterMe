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
    private Set<MessageDTO> messages  = new HashSet<>();
    private int messageId;
    private int reciverId;
    private String subject;
    private String body;

	public MessageDTO() {
		// TODO Auto-generated constructor stub
	}

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.body = message.getBody();
        this.subject = message.getSubject();
        this.reciverId = message.getReciverId();
        this.userId = message.getUser() != null ? message.getUser().getUserId() : 0;
        this.messageId = message.getMessage() == null ? 0 : message.getMessage().getId();
        this.messages = converter(message.getMessages());
    }

    public Set<MessageDTO> converter(Set<Message> messages) {
        Set<MessageDTO> messageDTOs = new HashSet<>();
        for (Message m : messages) {
            MessageDTO messageDTO = new MessageDTO(m);
            messageDTOs.add(messageDTO);
        }
        return messageDTOs;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Set<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(Set<MessageDTO> messages) {
		this.messages = messages;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
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
