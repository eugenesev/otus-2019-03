package ru.otus.hw13.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.otus.hw13.domain.HomeAddress;
import ru.otus.hw13.domain.PhoneDataSet;
import ru.otus.hw13.domain.User;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "ru.otus.hw13")
@EnableTransactionManagement


public class HibernateConfig {

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "create");
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "false");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:OtusHW14DB;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("admin");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Autowired
    @Bean
    public SessionFactory sessionFactory(DataSource dataSource){
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().mergeProperties(hibernateProperties());
        MetadataSources metadataSources = new MetadataSources(new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .applySetting(Environment.DATASOURCE, dataSource)
                .build());

        Class[] annotatedClasses = new Class[]{User.class, HomeAddress.class, PhoneDataSet.class};
        Arrays.stream(annotatedClasses).forEach(metadataSources::addAnnotatedClass);
        Metadata metadata = metadataSources.getMetadataBuilder().build();

        return metadata.getSessionFactoryBuilder().build();
    }

    //    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("ru.otus.hw13.domain");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }
}
