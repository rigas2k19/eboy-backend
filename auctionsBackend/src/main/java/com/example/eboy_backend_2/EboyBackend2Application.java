package com.example.eboy_backend_2;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EboyBackend2Application {

	public static void main(String[] args) {
		SpringApplication.run(EboyBackend2Application.class, args);
	}

	/* For password encode */
	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");   // θα πρεπει να περνανε μεσα απο ssl
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");    // ολα τα urls
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(redirectConnector());
		return tomcat;
	}

	/* 2. Οριζεται connector για ανακατεύθυνση ολων των αιτησεων απο το αρχικο μη κρυπτογραφημενο port
	 * στο κρυπτογραφημενο. Στο front-end για να εχω ssl το μονο που πρεπει να αλλαξω ειναι
	 * στο user service κανω το url με port το 8443. Τρ θα πρεπει να πιστοποιει την ταυτοτητα του χρηστη
	 * προκειμενου να μπορει να καταναλωσει τις υπηρεσιες. Αυτο πρεπει να υλοποιηθει και απο την πλευρα
	 * του backend και του frontend (authentication-authorization). Φτιάχνω εναν καταλογο security
	 * που αντιστοιχει σε ενα package security εντος του οποιου εχω μια κλαση websecurity. */
	private Connector redirectConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}

	/* Για καθε μονοπατι επιτρεπεται crossorigin request απο παντου */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")	// *
						.allowedHeaders("*")	//
						.allowedOrigins("*")
						.allowedMethods("*");	//
			}
		};
	}
}