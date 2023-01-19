package com.example.projectemployeeretro;

import com.example.projectemployeeretro.config.ConfigurationPropertiesEmail;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.jwt.JwtUtils;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.scope.ScopeBean;
import com.example.projectemployeeretro.spring_event.CustomPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Slf4j
@SpringBootApplication
@EnableAsync
@EnableCaching
//@EnableScheduling
//@EnableConfigurationProperties
public class ProjectEmployeeRetroApplication implements CommandLineRunner {
	@Value("${server.port}")
	private String port;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	CustomPublisher publisher;

	public static void main(String[] args) {
		System.out.println("> Trước khi IoC Container được khởi tạo");
		ApplicationContext context = SpringApplication.run(ProjectEmployeeRetroApplication.class, args);
		System.out.println("> Sau khi IoC Container được khởi tạo");
		ScopeBean bean1 = context.getBean(ScopeBean.class);
		ScopeBean bean2 = context.getBean(ScopeBean.class);
		System.out.println(bean1);
		System.out.println(bean2);
		System.out.println("> Trước khi IoC Container destroy bean");
		((ConfigurableApplicationContext) context).getBeanFactory().destroyBean(bean1);
		System.out.println(bean1);
		System.out.println(bean2);
		System.out.println("> Sau khi IoC Container destroy bean");
		//		SpringApplication application = new SpringApplication(ProjectEmployeeRetroApplication.class);
//		ConfigurableEnvironment environment = new StandardEnvironment();
//		environment.setActiveProfiles("dev");
//		application.setEnvironment(environment);
//		ApplicationContext context = application.run(args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(port);
		System.out.println(jwtUtils.getJwtExp() + "============" + jwtUtils.getJwtRefreshExpirationMs());
	}
	@Bean
	CommandLineRunner runner(){
		return args -> {
			System.out.println(Thread.currentThread().getName() + ": Publisher");
			System.out.println(Thread.currentThread().getName() + ": Tạo sự kiện");
			publisher.doEvent("coding");
			System.out.println(Thread.currentThread().getName() + ": Publisher kết thúc sự kiện");
		};
	}

}
