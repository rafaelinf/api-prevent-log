package org.com.prevent.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.com.prevent.domain.Log;
import org.com.prevent.service.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class LogRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private LogRepository logRepository;
	
    
    
    @Test
    public void whenFindByIp_thenReturnLog() {
        // given

        Log log = new Log();
        //log.setDate(new Date());
        log.setIp("255.255.255.555");
        log.setRequest("GET / HTTP/1.1");
        log.setStatus(200);
        log.setUseragent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.1.2 Safari/603.3.8");
        
        entityManager.persist(log);
        entityManager.flush();
     
        // when
        Log found = logRepository.findByIp("255.255.255.555");
     
        // then
        assertThat(found.getIp()).isEqualTo(log.getIp());
    }
    
}
