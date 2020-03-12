package org.superbiz.moviefun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.superbiz.moviefun.DatabaseServiceCredentials;
import javax.sql.DataSource;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean actionServletRegistration(ActionServlet actionServlet) {
        return new ServletRegistrationBean(actionServlet, "/moviefun/*");
    }
	
	@Bean
    public DatabaseServiceCredentials dbServiceCred(String vcapServices) {
      DatabaseServiceCredentials dbServiceCred = new DatabaseServiceCredentials(vcapServices);
    return dbServiceCred;
   }
	
	@Bean
    public DataSource albumsDataSource(DatabaseServiceCredentials serviceCredentials) {
      MysqlDataSource dataSource = new MysqlDataSource();
      dataSource.setURL(serviceCredentials.jdbcUrl("albums-mysql"));
    return dataSource;
   }
   
   
}
