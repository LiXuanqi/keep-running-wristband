package app;

import app.domain.UnitInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LocationServiceApplication {
    public static void main(String[] args) {
        UnitInfo unitInfo = new UnitInfo();
        unitInfo.setBandMake("FitBit");
        SpringApplication.run(LocationServiceApplication.class, args);
    }
}
