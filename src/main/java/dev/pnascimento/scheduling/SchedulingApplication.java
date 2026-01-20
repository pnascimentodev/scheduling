package dev.pnascimento.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
public class SchedulingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulingApplication.class, args);
	}

	@RestController
	public static class SwaggerRedirectController {
		@GetMapping("/swagger-ui/index.html")
		public RedirectView swaggerUiIndexRedirect() {
			return new RedirectView("/swagger-ui.html");
		}

		@GetMapping("/swagger-ui/")
		public RedirectView swaggerUiSlashRedirect() {
			return new RedirectView("/swagger-ui.html");
		}
	}

	@Configuration
	public static class SwaggerWebMvcConfig implements WebMvcConfigurer {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/swagger", "/swagger-ui.html");
		}
	}

}
