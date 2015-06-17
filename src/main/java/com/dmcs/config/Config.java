package main.java.com.dmcs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by chrustu on 17.06.2015.
 */
@Configuration
@ComponentScan({"pl.dmcs"})
@ImportResource("classpath:appContext.xml")
@EnableAspectJAutoProxy
public class Config {
}
