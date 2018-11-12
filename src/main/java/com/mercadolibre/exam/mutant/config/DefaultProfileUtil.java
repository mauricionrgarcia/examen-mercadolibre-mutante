package com.mercadolibre.exam.mutant.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;

/**
 * Utility class to load a Spring profile to be used as default when there is no
 * <code>spring.profiles.active</code> set in the environment or as command line
 * argument.<br>
 * If the value is not available then <code>dev</code> profile will be used as
 * default.
 *
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 07/11/2018 01:00:13
 */
public final class DefaultProfileUtil {

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
	private static final Object SPRING_PROFILE_DEVELOPMENT = "dev";

	/**
	 * Set a default profile to use when no profile is configured.
	 *
	 * @param app the Spring application
	 */
	public static void addDefaultProfile(SpringApplication app) {
		Map<String, Object> defProperties = new HashMap<>();
		defProperties.put(SPRING_PROFILE_DEFAULT, SPRING_PROFILE_DEVELOPMENT);
		app.setDefaultProperties(defProperties);
	}

}