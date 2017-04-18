package com.butyter.contact;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
Main Spring Configuration
 */
@Configuration
@ImportResource("classpath:/spring/api-context.xml")
@ComponentScan(basePackageClasses = ApplicationSpringConfiguration.class)
public class ApplicationSpringConfiguration {


  
    }