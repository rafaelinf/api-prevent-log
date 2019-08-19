package org.com.prevent.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import org.com.prevent.domain.Log;
import org.com.prevent.domain.LogVO;
import org.springframework.batch.item.ItemProcessor;

public class LogItemProcessor implements ItemProcessor<LogVO, Log> {

    private static final Logger logger = LoggerFactory.getLogger(LogItemProcessor.class);

	@Override
	public Log process(LogVO logVO) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Log log = new Log();
        log.setDate(sdf.parse(logVO.getDate()));
        log.setIp(logVO.getIp());
        log.setRequest(logVO.getRequest());
        log.setStatus(logVO.getStatus());
        log.setUseragent(logVO.getUseragent());
		
        logger.info("Converting (" + logVO.getIp() + ") into (" + logVO.getIp() + ")");

		return log;
	}



}
