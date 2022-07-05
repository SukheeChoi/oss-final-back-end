package ows.edu.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class DataSourceConfig {
	@Bean
	  public DataSource dataSource() {
	    log.info("실행");
	    HikariConfig config = new HikariConfig();
	    config.setDriverClassName("org.mariadb.jdbc.Driver");
	    config.setJdbcUrl("jdbc:mariadb://localhost:3306/osstem");
//	    config.setJdbcUrl("jdbc:mariadb://kosa1.iptime.org:50121/osstem");

	    config.setUsername("root");
	    config.setPassword("shsh");
//	    config.setPassword("1111");
//	    config.setPassword("mariadb")

	    config.setMaximumPoolSize(3);
	    HikariDataSource hikariDataSource = new HikariDataSource(config);
	    return hikariDataSource;
	}
}
