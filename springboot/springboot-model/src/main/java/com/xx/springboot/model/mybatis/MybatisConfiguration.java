package com.xx.springboot.model.mybatis;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;

@Configuration
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisConfiguration {

	private static Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

	@Value("${mybatis.config}")
	private String config;

	@Value("${mybatis.mapperLocations}")
	private Resource[] mapperLocations;

	@Value("${mybatis.typeAliasesPackage}")
	private String typeAliasesPackage;

	@Value("${mybatis.typeHandlersPackage}")
	private String typeHandlersPackage;
	
	@Value("${mybatis.checkConfigLocation}")
	private boolean checkConfigLocation;

	@Value("${mybatis.executorType}")
	private ExecutorType executorType;

	@Autowired(required = false)
	private Interceptor[] interceptors;

	@Autowired
	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	@Bean(name = "sqlSessionFactory")
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		if (StringUtils.hasText(this.config)) {
			factory.setConfigLocation(this.resourceLoader.getResource(this.config));
		} else {
			if (this.interceptors != null && this.interceptors.length > 0) {
				factory.setPlugins(this.interceptors);
			}
			factory.setTypeAliasesPackage(this.typeAliasesPackage);
			factory.setTypeHandlersPackage(this.typeHandlersPackage);
			int length = this.mapperLocations.length;
			if (length != 1 || StringUtils.hasText(this.mapperLocations[0].getFilename())) {
				factory.setMapperLocations(this.mapperLocations);
			}
		}
		return factory.getObject();
	}

	@Bean
	@ConditionalOnMissingBean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory, this.executorType);
	}

	@Bean
	public PageHelper pageHelper(DataSource dataSource) {
		logger.info("注册MyBatis分页插件PageHelper");
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}
	
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer() {
//		System.out.println("Mapper Scan Package：" + basePackage);
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//		mapperScannerConfigurer.setBasePackage(basePackage);
//		Properties properties = new Properties();
//		// 这里要特别注意，不要把MyMapper放到 basePackage 中，也就是不能同其他Mapper一样被扫描到。
//		properties.setProperty("mappers", BaseMapper.class.getName());
//		properties.setProperty("notEmpty", "false");
//		properties.setProperty("IDENTITY", "MYSQL");
//		mapperScannerConfigurer.setProperties(properties);
//		return mapperScannerConfigurer;
//	}
}
