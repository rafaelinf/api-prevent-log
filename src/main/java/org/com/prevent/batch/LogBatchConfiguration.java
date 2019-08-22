package org.com.prevent.batch;

import javax.sql.DataSource;

import org.com.prevent.domain.Log;
import org.com.prevent.domain.LogVO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class LogBatchConfiguration extends DefaultBatchConfigurer {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<LogVO> reader() {
    	
        FlatFileItemReader<LogVO> r = new FlatFileItemReader<>();
        r.setResource(new FileSystemResource("C:\\Users\\rafael\\workspace_jee_oxygen\\api-prevent-log\\src\\main\\resources\\access.log"));
        r.setLineMapper(new DefaultLineMapper<LogVO>() {
            {
                this.setLineTokenizer(new DelimitedLineTokenizer("|") {
                    {
                        this.setNames(new String[]{"date", "ip", "request", "status", "useragent"});
                    }
                });
                this.setFieldSetMapper(new BeanWrapperFieldSetMapper<LogVO>() {
                    {
                        this.setTargetType(LogVO.class);
                    }
                });
            }
        });
        return r;	
    	
    }

    @Bean
    public LogItemProcessor processor() {
        return new LogItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Log> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Log>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO log_tb (log_date, log_ip, log_request, log_status, log_useragent) VALUES (:date, :ip, :request, :status, :useragent)")
            .dataSource(dataSource)
            .build();
    }   

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Log> writer) {
        return stepBuilderFactory.get("step1")
            .<LogVO, Log> chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
    }
    
    @Autowired
	@Override
    public void setDataSource(DataSource dataSource) {
    }
}
