package ua.epam.spring.hometask.config;

import no.api.freemarker.java8.Java8ObjectWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Version;

import javax.annotation.PostConstruct;

/**
 * Created by Vladimir_Vysokomorny on 06-Dec-17.
 */

@Configuration // AB: added
public class FreeMarkerConfigurerJava8 extends FreeMarkerConfigurer {

    // AB: added
    @Autowired
    private freemarker.template.Configuration originalFreemarkerConfig;

    // AB: added
//    @PostConstruct
//    public void postConstruct() {
//        System.out.println("----------------------------------");
//        System.out.println("----------------------------------");
//        freemarker.template.Configuration configuration = new freemarker.template.Configuration();
//        configuration.setObjectWrapper(new Java8ObjectWrapper(freemarker.template.Configuration.VERSION_2_3_23));
//        setConfiguration(configuration);
//    }
    public void init() {
        System.out.println("Configuration in init: " + getConfiguration());
        getConfiguration().setObjectWrapper(
                new Java8ObjectWrapper(freemarker.template.Configuration.getVersion())); // VERSION_2_3_26
    }


}
