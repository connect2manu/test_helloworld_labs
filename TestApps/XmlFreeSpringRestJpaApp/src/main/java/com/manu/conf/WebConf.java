package com.manu.conf;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.dialect.MySQL5Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.manu.restapi.controller.CustomerController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.manu.restapi" })
@EnableTransactionManagement
public class WebConf extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebConf.class);

	// @Value("#{mysqlDataSource}")
	// private javax.sql.DataSource mysqlDataSource;

	// @Value("#{oracleDataSource}")
	// private javax.sql.DataSource oracleDataSource;

	// @Autowired
	// ServletContext servletContext;

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		logger.info("WebConfiguration | configureContentNegotiation()");
		configurer.favorPathExtension(false).favorParameter(true).parameterName("mediaType")
				.
				// ignoreAcceptHeader(true).
				useJaf(false).defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML).mediaType("json", MediaType.APPLICATION_JSON);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		logger.info("+ WebConfiguration | configureMessageConverters()");
		converters.add(jacksonMessageConvertor());
		converters.add(xmlMessageConvertor());
		logger.info("- WebConfiguration | configureMessageConverters()");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("WebConfiguration | addInterceptors()");
		// registry.addInterceptor(new AuthenticationInterceptor());
		// registry.addInterceptor(new GeoLocatorInterceptor());
		// registry.addInterceptor(new
		// ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
		// registry.addInterceptor(new
		// SecurityInterceptor()).addPathPatterns("/secure/*");
	}

	@Bean
	public MappingJackson2HttpMessageConverter jacksonMessageConvertor() {
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		jackson2HttpMessageConverter.setPrettyPrint(true);
		jackson2HttpMessageConverter.getObjectMapper().configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		jackson2HttpMessageConverter.getObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		return jackson2HttpMessageConverter;
	}

	@Bean
	public Jaxb2RootElementHttpMessageConverter xmlMessageConvertor() {
		Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter = new Jaxb2RootElementHttpMessageConverter();
		return jaxb2RootElementHttpMessageConverter;
	}

	@Bean(name = "txManagerMySqlDB")
	public PlatformTransactionManager transactionManagerMySql() {
		return new JpaTransactionManager(entityManagerFactoryBeanForMySql().getObject());
	}

	@Bean(name = "txManagerOracleDB")
	public PlatformTransactionManager transactionManagerOracle() {
		return new JpaTransactionManager(entityManagerFactoryBeanForOracle().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanForMySql() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceUnitName("CustomerMySqlUnit");
		emf.setDataSource(dataSourceMySql());
		emf.setPackagesToScan(new String[] { "com.manu.restapi.dal.models" });
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		// emf.setJpaPropertyMap(jpaProperties());
		emf.setJpaProperties(jpaHibernateMySqlProperties());
		return emf;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanForOracle() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceUnitName("ProductOracleUnit");
		emf.setDataSource(dataSourceOracle());
		emf.setPackagesToScan(new String[] { "com.manu.restapi.dal.models.oracle" });
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		// emf.setJpaPropertyMap(jpaProperties());
		emf.setJpaProperties(jpaHibernateOracleProperties());
		return emf;
	}

	@Bean
	public DataSource dataSourceMySql() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("admin");
		dataSource.setPassword("admin#123");
		return dataSource;
	}

	@Bean
	public DataSource dataSourceOracle() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/*
		 * TODO: Change this for oracle.
		 */
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test2");
		dataSource.setUsername("admin");
		dataSource.setPassword("admin#123");
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		// hibernateJpaVendorAdapter.setShowSql(true);
		// hibernateJpaVendorAdapter.setGenerateDdl(true);
		// hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	/*	public Map<String, Object> jpaProperties() {
			Map<String, Object> props = new HashMap<String, Object>();
			props.put("hibernate.dialect", MySQL5Dialect.class.getName());
			props.put("hibernate.cache.provider_class", HashtableCacheProvider.class.getName());
			return props;
		}*/

	Properties jpaHibernateMySqlProperties() {
		return new Properties() {
			{ // Hibernate Specific:
				setProperty("hibernate.dialect", MySQL5Dialect.class.getName());
				// setProperty("hibernate.hbm2ddl.auto", "create-drop");
			}
		};
	}

	Properties jpaHibernateOracleProperties() {
		/*
		 * TODO: Change this for oracle.
		 */
		return new Properties() {
			{ // Oracle Specific:
				setProperty("hibernate.dialect", MySQL5Dialect.class.getName());
			}
		};
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
