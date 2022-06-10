package ows.edu.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@MapperScan(basePackages = {"ows.edu.dao"})
public class MyBatisConfig {
	
	@Resource
	private DataSource dataSource;	//DataSourceConfig에서 관리 객체로 DataSource를 등록했기 때문에 사용 가능
	
	@Resource
	WebApplicationContext wac;	//실행환경 객체 (객체들을 관리하는 IOC역할 컨테이너 역할을 한다.)
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource);
		ssfb.setConfigLocation(wac.getResource("classpath:mybatis/mapper-config.xml"));
		ssfb.setMapperLocations(wac.getResources("classpath:mybatis/mapper/*.xml"));
		return ssfb.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory ssf) throws Exception {
		SqlSessionTemplate sst = new SqlSessionTemplate(ssf);
		return sst;
	}
}
