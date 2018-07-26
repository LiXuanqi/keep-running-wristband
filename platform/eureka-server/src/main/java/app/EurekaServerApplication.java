package app;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
