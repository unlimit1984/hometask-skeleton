package ua.epam.spring.hometask.config;

import freemarker.template.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ua.epam.spring.hometask.service.strategy.BirthdayStrategy;
import ua.epam.spring.hometask.service.strategy.DiscountStrategy;
import ua.epam.spring.hometask.service.strategy.TicketCountStrategy;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vladimir on 19.10.2017.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan({
        "ua.epam.spring.hometask.repository.jdbc",
        "ua.epam.spring.hometask.service",
        "ua.epam.spring.hometask.aspects"
})
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.DERBY).addScript("init_db.sql").addScript("populate_db.sql");
        return builder.build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public List<DiscountStrategy> strategies() {
        return Arrays.asList(birthdayStrategy(), ticketCountStrategy());
    }

    @Bean
    public DiscountStrategy birthdayStrategy() {
        return new BirthdayStrategy();
    }

    @Bean
    public DiscountStrategy ticketCountStrategy() {
        return new TicketCountStrategy();
    }

    // AB: added (2.3.23 - the latest version could be used (perhaps related to my workstation))
    // AB: you can try to use 2.3.26 on yours
    @Bean
    freemarker.template.Configuration freeMarkerConfig() {
        return new freemarker.template.Configuration(new Version(2, 3, 23));
    }

}
