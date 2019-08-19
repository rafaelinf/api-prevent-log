package org.com.prevent.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="log_tb")
public class Log {

    @Id
    @GeneratedValue
    @Column(name = "log_id")
    private long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "log_date")
    private Date  date;

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
    
	public Log(long id, Date date, String ip, String request, Integer status, String useragent) {
		this.id = id;
		this.date = date;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.useragent = useragent;
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
