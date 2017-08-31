package fi.espoo.pythia.backend;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author jukkak
 * 
 * See also http://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
 * and http://www.baeldung.com/spring-data-jpa-multiple-databases
 * 
 *
 */

@Configuration
@EnableJpaRepositories(basePackages = "fi.espoo.pythia.backend.repos")
@EnableTransactionManagement
public class AppilicationConfig {

	@Bean
	  public DataSource dataSource() {

	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	    return builder.setType(EmbeddedDatabaseType.HSQL).build();
	  }

	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);

	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan("com.acme.domain");
	    factory.setDataSource(dataSource());
	    return factory;
	  }
	  @Bean
	    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, 
	                                                                Environment env) {
	        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	        entityManagerFactoryBean.setDataSource(dataSource);
	        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	        entityManagerFactoryBean.setPackagesToScan("net.petrikainulainen.springdata.jpa.todo");
	 
	        Properties jpaProperties = new Properties();
	     
	        //Configures the used database dialect. This allows Hibernate to create SQL
	        //that is optimized for the used database.
	        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
	 
	        //Specifies the action that is invoked to the database when the Hibernate
	        //SessionFactory is created or closed.
	        jpaProperties.put("hibernate.hbm2ddl.auto", 
	                env.getRequiredProperty("hibernate.hbm2ddl.auto")
	        );
	 
	        //Configures the naming strategy that is used when Hibernate creates
	        //new database objects and schema elements
	        jpaProperties.put("hibernate.ejb.naming_strategy", 
	                env.getRequiredProperty("hibernate.ejb.naming_strategy")
	        );
	 
	        //If the value of this property is true, Hibernate writes all SQL
	        //statements to the console.
	        jpaProperties.put("hibernate.show_sql", 
	                env.getRequiredProperty("hibernate.show_sql")
	        );
	 
	        //If the value of this property is true, Hibernate will format the SQL
	        //that is written to the console.
	        jpaProperties.put("hibernate.format_sql", 
	                env.getRequiredProperty("hibernate.format_sql")
	        );
	 
	        entityManagerFactoryBean.setJpaProperties(jpaProperties);
	 
	        return entityManagerFactoryBean;
	    }
	  @Bean
	    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	        JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(entityManagerFactory);
	        return transactionManager;
	    }
	  @Bean
	  public PlatformTransactionManager transactionManager() {

	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory());
	    return txManager;
	  }
}
