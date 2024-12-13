package com.something.demo.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableBatchProcessing
public class BatchConfig {
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    public JobRepository getJobRepository() throws Exception {
//        return createNewJobRepository();
//    }
//
//    @Override
//    public PlatformTransactionManager getTransactionManager() throws Exception {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Override
//    public JobLauncher getJobLauncher() throws Exception {
//        return null;
//    }
//
//    @Override
//    public JobExplorer getJobExplorer() throws Exception {
//        return null;
//    }
//
//    public JobRepository createNewJobRepository() throws Exception {
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setTransactionManager(getTransactionManager());
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager platformTransactionManager() throws Exception {
//        return getTransactionManager();
//    }
//    @Bean
//    public JobRepository jobRepository() throws Exception {
//        return getJobRepository();
//    }
}
