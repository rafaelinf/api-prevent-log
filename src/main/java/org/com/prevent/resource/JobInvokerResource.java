package org.com.prevent.resource;

import java.io.FileOutputStream;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
@RestController
public class JobInvokerResource {
 
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job importUserJob;
 
    @PostMapping("/invokejob")
    public ResponseEntity<Object>  handleFileUpload(
    		@RequestParam("file") MultipartFile file) 
    				throws Exception{
    	
        FileOutputStream outputStream = new FileOutputStream(file.getOriginalFilename());
        byte[] strToBytes = file.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    	        
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
        jobLauncher.run(importUserJob, jobParameters);
    	
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    
}
