package cl.ubicua.smsmodulo.app.models.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "sms_status")
public class SMSstatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@NotEmpty*/
    @Column(name = "sms_id")
    private String smssid;

    /*@NotEmpty*/
    @Column(name = "sms_status")
    private String status;

    /*@NotEmpty*/

    @Column(name = "to_numero")
    private String to;

    /*@NotEmpty*/

    @Column(name = "from_numero")
    private String desde;


    @Column(name = "message_id")
    private String messageid;

    @Column(name = "account_id")
    private String accountid;

    @Column(name = "message_status")
    private String messagestatus;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "fecha_evento")
    private Date fecha;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmssid() {
        return smssid;
    }

    public void setSmssid(String smssid) {
        this.smssid = smssid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String from) {
        this.desde = from;
    }



    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getMessagestatus() {
        return messagestatus;
    }

    public void setMessagestatus(String messagestatus) {
        this.messagestatus = messagestatus;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
