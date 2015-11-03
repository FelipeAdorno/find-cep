package br.com.find.cep.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validator;

/**
 * The type Find postal code project.
 * @author Felipe Adorno (felipeadsc@gmail.com)
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.find.cep")
public class FindPostalCodeProject {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(FindPostalCodeProject.class, args);
	}

	@Primary
	@Bean
	public Validator validator(){
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

}