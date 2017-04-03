package ae.tutorme.dto;


import ae.tutorme.model.Authorization;

import java.io.Serializable;

/**
 * Created by almehairbi on 2/17/17.
 */


public class AuthorizationDTO implements Serializable
{

    private int id;
    private String role;
    private String userName;
    
    public AuthorizationDTO() {
		// TODO Auto-generated constructor stub
	}

    public AuthorizationDTO(Authorization authorization) {
        this.id = authorization.getId();
        this.userName = authorization.getUser().getUserName();
        this.role = authorization.getRole();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
