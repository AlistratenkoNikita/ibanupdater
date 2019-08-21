package ua.com.tascombank.ibanupdater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class IbanupdaterApplication implements CommandLineRunner {

    @Autowired
    IbanUpdateService ibanUpdateService;

    private static Logger logger = LoggerFactory.getLogger(IbanupdaterApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IbanupdaterApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("Updates generation has started");
        ibanUpdateService.generateUpdatesForPayments();
        ibanUpdateService.generateUpdatesForTemplates();
        logger.info("Updates generation has finished");
    }
}
