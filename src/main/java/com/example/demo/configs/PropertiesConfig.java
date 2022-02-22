package com.example.demo.configs;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Configuration
@PropertySources({
        @PropertySource("classpath:contact.properties")
})
@Data
@Component
public class PropertiesConfig {

}
