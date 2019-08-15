package org.com.prevent.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="log_tb")
@NamedQueries({
	@NamedQuery(name="Log.findByAll", query="SELECT l FROM Log l")
	})
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_seq")
    @SequenceGenerator(name = "log_seq", sequenceName = "log_seq")
    @Column(name = "log_id")
    private long id;
    
    @Column(name = "log_date")
    private Date date;

    @Column(name = "log_ip")
    private String ip;
    
    @Column(name = "log_request")
    private String request;
    
    @Column(name = "log_status")
    private Integer status;
    
    @Column(name = "log_useragent")
    private String useragent;
    
    public Log() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}

}
