package igor.henrique;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RestaurantSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantSystemApplication.class, args);

		log.info("""
                 \s
				██╗ ██████╗  ██████╗ ██████╗\s
				██║██╔════╝ ██╔═══██╗██╔══██╗
				██║██║  ███╗██║   ██║██████╔╝
				██║██║   ██║██║   ██║██╔══██╗
				██║╚██████╔╝╚██████╔╝██║  ██║
				╚═╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═╝
                 \s
                  DEFAULT PROJECT :: 0.1
                \s""");
	}

}
