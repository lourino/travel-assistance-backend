package lourino.chemane.mz.travel.assistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Lourino Chemane
 */
@SpringBootApplication
public class TravelAssistanceApp {

    private static ConfigurableApplicationContext appContext;

    public static void main(String[] args) {
        appContext = SpringApplication.run(TravelAssistanceApp.class, args);
    }

    public static <T> T getBean(Class<T> beanClass){
        return appContext.getBean(beanClass);
    }
}
