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

}
