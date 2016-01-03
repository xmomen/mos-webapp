package com.xmomen.mos.module.core;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.dao.impl.MybatisDaoImpl;
import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.framework.support.SpringContextUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Spring Boot launch file.
 */
@SpringBootApplication
@MapperScan(basePackages = "com.**.mapper", markerInterface = MybatisMapper.class)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application  implements EnvironmentAware {

	private RelaxedPropertyResolver propertyResolver;

	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/**/mapper/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.**.entity");
		sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:config/mybatis-config.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(destroyMethod = "clearCache")
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}

	@Bean
	public MybatisDao mybatisDao() throws Exception {
		MybatisDaoImpl mybatisDao = new MybatisDaoImpl();
		mybatisDao.setSqlSessionTemplate(sqlSessionTemplate());
		return mybatisDao;
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		org.apache.commons.dbcp.BasicDataSource dataSource = new org.apache.commons.dbcp.BasicDataSource();
		dataSource.setUsername(this.propertyResolver.getProperty("spring.datasource.username"));
		dataSource.setPassword(this.propertyResolver.getProperty("spring.datasource.password"));
		dataSource.setUrl(this.propertyResolver.getProperty("spring.datasource.url"));
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setValidationQuery("SELECT 1 FROM DUAL");
		dataSource.setValidationQueryTimeout(3000);
		dataSource.setDefaultAutoCommit(true);
		return dataSource;
	}

	@Bean
	@Primary
	public SpringContextUtil springContextUtil(){
		SpringContextUtil springContextUtil = new SpringContextUtil();
		return springContextUtil;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}