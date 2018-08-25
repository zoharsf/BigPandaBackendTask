package backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import javax.annotation.PostConstruct;
import java.util.*;

@Configuration
@Slf4j
public class PropertiesLogger {
	@Autowired
	private AbstractEnvironment environment;
	
	@PostConstruct
	public void printProperties() {
		
		log.info("\n\n##############################\nAPPLICATION PROPERTIES SOURCES\n##############################");
		
		Set<String> properties = new TreeSet<>();
		for (PropertiesPropertySource p : findPropertiesPropertySources()) {
			log.info(p.toString());
			properties.addAll(Arrays.asList(p.getPropertyNames()));
		}
		
		log.info("\n\n#############################\nAPPLICATION PROPERTIES VALUES\n#############################");
		print(properties);
	}
	
	private List<PropertiesPropertySource> findPropertiesPropertySources() {
		List<PropertiesPropertySource> propertiesPropertySources = new LinkedList<>();
		for (PropertySource<?> propertySource : environment.getPropertySources()) {
			if (propertySource instanceof PropertiesPropertySource) {
				propertiesPropertySources.add((PropertiesPropertySource) propertySource);
			}
		}
		return propertiesPropertySources;
	}
	
	private void print(Set<String> properties) {
		for (String propertyName : properties) {
			log.info("{}={}", propertyName, environment.getProperty(propertyName));
		}
	}
}
