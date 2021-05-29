package com.health.service.api.config.datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "com.health.service.api.user"
        },
        entityManagerFactoryRef = "primaryEntityManager",
        transactionManagerRef = "primaryTransactionManager")
public class PrimaryDataSourceConfiguration {

    @Primary
    @Bean(name = "primaryDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties primaryDataSourceProperties() { return new DataSourceProperties(); }

    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dbcp2")
    public DataSource primaryDataSource(@Qualifier("primaryDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(BasicDataSource.class).build();
    }

    @Primary
    @Bean(name = "primaryJpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa")
    public JpaProperties primaryJpaProperties() { return new JpaProperties(); }

    @Primary
    @Bean(name = "primaryHibernateProperties")
    @ConfigurationProperties(prefix = "spring.jpa.hibernate")
    public HibernateProperties primaryHibernateProperties() { return new HibernateProperties(); }

    @Primary
    @Bean(name = "primaryEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("primaryJpaProperties") JpaProperties jpaProperties,
                                                                       @Qualifier("primaryHibernateProperties") HibernateProperties hibernateProperties,
                                                                       @Qualifier("primaryDataSource") DataSource dataSource) {
        AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(jpaProperties.isShowSql());
        if (jpaProperties.getDatabase() != null) {
            adapter.setDatabase(jpaProperties.getDatabase());
        }
        if (jpaProperties.getDatabasePlatform() != null) {
            adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        }
        adapter.setGenerateDdl(jpaProperties.isGenerateDdl());

        Map<String, Object> jpaPropertiesMap = new LinkedHashMap<>(jpaProperties.getProperties());
        Map<String, Object> hibernatePropertiesMap = new LinkedHashMap<>(hibernateProperties
                .determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings().ddlAuto(() -> "none")));

        String[] packageToScan = {
                "com.health.service.api.user.entity"
        };

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceUnitName("primaryUnit");
        entityManagerFactoryBean.setJpaVendorAdapter(adapter);
        entityManagerFactoryBean.getJpaPropertyMap().putAll(jpaPropertiesMap);
        entityManagerFactoryBean.getJpaPropertyMap().putAll(hibernatePropertiesMap);
        entityManagerFactoryBean.setPackagesToScan(packageToScan);

        return entityManagerFactoryBean;
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
