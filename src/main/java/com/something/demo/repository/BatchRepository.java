package com.something.demo.repository;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MongoJobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;

public class BatchRepository {

//    @Bean
//    public JobRepository jobRepository(MongoTemplate mongoTemplate, MongoTransactionManager transactionManager) throws Exception {
//        MongoJobRepositoryFactoryBean jobRepositoryFactoryBean = new MongoJobRepositoryFactoryBean();
//        jobRepositoryFactoryBean.setMongoOperations(mongoTemplate);
//        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
//        jobRepositoryFactoryBean.afterPropertiesSet();
//        return jobRepositoryFactoryBean.getObject();
//    }
}
