package ae.tutorme.dto;

import ae.tutorme.model.Activation;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by almehairbi on 2/17/17.
 */

public class ActivationDTO implements Serializable
{

    private int id;
    private String activationCode;
    private Date expiryDate;
    private String userName;
    
    public ActivationDTO() {
		// TODO Auto-generated constructor stub
	}

    public ActivationDTO(Activation activation) {
        this.id = activation.getId();
        this.activationCode = activation.getactivationCode();
        this.expiryDate = activation.getExpiryDate();
        this.userName = activation.getUser().getUserName();
    }



    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getactivationCode() {
        return activationCode;
    }

    public void setactivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getUser() {
        return userName;
    }

    public void setUser(String userName) {
        this.userName = userName;
    }
}
