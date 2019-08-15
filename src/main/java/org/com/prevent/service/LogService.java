package org.com.prevent.service;

import java.util.Date;
import java.util.List;

import org.com.prevent.domain.Log;
import org.com.prevent.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;
	
    public Log findByIp(String ip) {
    	Log logResult = logRepository.findByIp(ip);
    	System.out.println("LOG IP: " + logResult.getId());
    	System.out.println("LOG IP: " + logResult.getIp());
    	return logResult;
    }
    
	public void save() {
		
		/*
        for (int i = 0; i < 1000; i++) {
			
	        Log log = new Log();
	        log.setDate(new Date());
	        log.setIp("255.255.255.255");
	        log.setRequest("GET / HTTP/1.1");
	        log.setStatus(200);
	        log.setUseragent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.1.2 Safari/603.3.8");
	        
	        logRepository.save(log);
	        
	        Log log1 = new Log();
	        log1.setDate(new Date());
	        log1.setIp("255.255.255.255");
	        log1.setRequest("GET / HTTP/1.1");
	        log1.setStatus(200);
	        log1.setUseragent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.1.2 Safari/603.3.8");
	        
	        logRepository.save(log1);
	        //System.out.println("\nfindAll()");
	        //logRepository.findAll().forEach(x -> System.out.println(x.getId()));

		}
        
        List<Log> listLog = logRepository.findAll();
        for (Log l : listLog) {
			System.out.println(l.getId());
		}
		
		*/
		
	}
}
