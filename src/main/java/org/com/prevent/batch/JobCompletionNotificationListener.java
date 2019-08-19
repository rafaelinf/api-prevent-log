package org.com.prevent.batch;

import org.com.prevent.domain.Log;
import org.com.prevent.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			jdbcTemplate.query("SELECT log_id, log_date, log_ip, log_request, log_status, log_useragent FROM log_tb",
				(rs, row) -> new Log(
					rs.getLong(1),
					rs.getDate(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getString(6))
			).forEach(person -> log.info("Found <" + person + "> in the database."));
			
		}
	}
	
}
