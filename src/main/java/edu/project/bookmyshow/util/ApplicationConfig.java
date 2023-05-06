package edu.project.bookmyshow.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig {

	@Bean
	
		@SuppressWarnings("rawtypes")
		public Docket getDocket() {
			Contact contact = new Contact("TYSS Team", null, "rajugowda0212@gmail.com");
			List<VendorExtension> extensions = new ArrayList<>();
			ApiInfo apiInfo = new ApiInfo("Shopping App - Angadi API",
					
					"BookMyShow-Clone-API is a project for online Ticket-Booking-System. The \r\n"
							+ "BookMyShow clone API will be used to book Movie tickets online, the application \r\n"
							+ "will allow users to select the location and then search for the movies based on that \r\n"
							+ "location, the user can select the Screen and number of seats they want, the \r\n"
							+ "application will help eliminating the need of physical ticket counters, this \r\n"
							+ "application will also auto update the show status, and maintains all the show \r\n"
							+ "history. \r\n"
							+ "",
					
					"1.0 first", "", contact, "", "", extensions);
			
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("edu.project.bookmyshow")).build()
					.apiInfo(apiInfo).useDefaultResponseMessages(false);
		
	}
}
