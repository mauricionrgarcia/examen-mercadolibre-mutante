package com.mercadolibre.exam.mutant;

import java.net.InetAddress;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.mercadolibre.exam.mutant.config.DefaultProfileUtil;

/**
 * Technical Test 'MercadoLibre'
 * <p>
 * Mutant Project. API REST to identify if a human is a mutant given the DNA and
 * store and make available the statistics of verifications the exposed method
 * (humans, mutants and ratio).
 * 
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 07/11/2018 01:00:07
 */
@SpringBootApplication
public class ExamenMercadolibreMutanteApplication {

	private static final Logger log = LoggerFactory.getLogger(ExamenMercadolibreMutanteApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ExamenMercadolibreMutanteApplication.class);
		DefaultProfileUtil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();

		String hostAddress = getHostAddress();
		String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).orElse("http");

		// @formatter:off
		log.info("\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running!\n"
						+ "Access URLs:\n\t"
						+ "Local: \t\t{}://localhost:{}\n\t"
						+ "External: \t{}://{}:{}\n\t"
						+ "Profile(s): \t{}\n"
				+ "----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol, env.getProperty("server.port"),
				protocol, hostAddress, env.getProperty("server.port"),
				env.getActiveProfiles());
		// @formatter:on
	}

	/**
	 * Return the hostaddress
	 *
	 * @param log instance off current log
	 * @return the host name, using 'localhost' as fallback
	 */
	private static String getHostAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			log.warn("The host name could not be determined, using 'localhost' as fallback");
			return "localhost";
		}
	}
}
