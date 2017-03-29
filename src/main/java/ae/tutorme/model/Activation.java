package ae.tutorme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by almehairbi on 2/17/17.
 */

@Entity
@Table(name = "ACTIVATION")
public class Activation
{

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;



    @Column(name = "ACTIVATION_CODE")
    private String activationCode;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private User user;

    public Activation(String activationCode, Date expiryDate, User user) {
        this.activationCode = activationCode;
        this.expiryDate = expiryDate;
        this.user = user;
    }

    public Activation(String activationCode, User user) {
        this.activationCode = activationCode;
        this.user = user;
    }

    public Activation() {
        this("", null);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
