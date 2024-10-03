package Lab.CarRentalSystem.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import Lab.CarRentalSystem.enums.SystemUserType;
import Lab.CarRentalSystem.models.Agency;
import Lab.CarRentalSystem.models.Automobile;
import Lab.CarRentalSystem.models.Bank;
import Lab.CarRentalSystem.repository.AgencyRepository;
import Lab.CarRentalSystem.repository.AutomobileRepository;
import Lab.CarRentalSystem.repository.BankRepository;

@Configuration
public class WebConfig implements WebMvcConfigurer, CommandLineRunner {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                                .allowedOrigins("http://localhost:3000")
                                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                                .allowedHeaders("*")
                                .allowCredentials(true);
        }

        @Autowired
        private AgencyRepository agencyRepository;

        @Autowired
        private AutomobileRepository automobileRepository;

        @Autowired
        private BankRepository bankRepository;

        @Override
        public void run(String... args) throws Exception {
                if (agencyRepository.count() == 0) {
                        Agency agency1 = new Agency("Agency", "123 Main St", SystemUserType.AGENCY, true,
                                        "password123", 0, null);
                        Agency agency2 = new Agency("Agency Two", "456 Elm St", SystemUserType.AGENCY, true,
                                        "password456", 0, null);

                        agencyRepository.saveAll(Arrays.asList(agency1, agency2));
                        System.out.println("Agências inicializadas com sucesso!");
                }

                if (automobileRepository.count() == 0) {
                        Automobile car1 = new Automobile(null, "Toyota Corolla", 150.0, 2020, "ABC1234", "Toyota",
                                        "Corolla",
                                        "XYZ-1234", null);
                        Automobile car2 = new Automobile(null, "Honda Civic", 160.0, 2021, "DEF5678", "Honda", "Civic",
                                        "XYZ-5678",
                                        null);
                        Automobile car3 = new Automobile(null, "Ford Mustang", 200.0, 2022, "GHI9012", "Ford",
                                        "Mustang",
                                        "XYZ-9012", null);
                        Automobile car4 = new Automobile(null, "Chevrolet Malibu", 140.0, 2019, "JKL3456", "Chevrolet",
                                        "Malibu",
                                        "XYZ-3456", null);
                        Automobile car5 = new Automobile(null, "Hyundai Elantra", 130.0, 2021, "MNO7890", "Hyundai",
                                        "Elantra",
                                        "XYZ-7890", null);
                        Automobile car6 = new Automobile(null, "Nissan Sentra", 125.0, 2020, "PQR1234", "Nissan",
                                        "Sentra",
                                        "XYZ-1235", null);
                        Automobile car7 = new Automobile(null, "Volkswagen Jetta", 155.0, 2021, "STU5678", "Volkswagen",
                                        "Jetta",
                                        "XYZ-5679", null);
                        Automobile car8 = new Automobile(null, "Kia Forte", 120.0, 2022, "VWX9012", "Kia", "Forte",
                                        "XYZ-9020",
                                        null);

                        automobileRepository.saveAll(
                                        Arrays.asList(car1, car2, car3, car4, car5, car6, car7, car8));
                        System.out.println("Carros inicializados com sucesso!");
                }

                if (bankRepository.count() == 0) {
                        Bank bank1 = new Bank("Bank of America", "100 N Tryon St, Charlotte, NC 28202",
                                        SystemUserType.BANK, true,
                                        "password", 5000.0, null);
                        Bank bank2 = new Bank("Wells Fargo", "420 Montgomery St, San Francisco, CA 94104",
                                        SystemUserType.BANK,
                                        true, "password", 10000.0, null);
                        Bank bank3 = new Bank("Chase Bank", "270 Park Ave, New York, NY 10017", SystemUserType.BANK,
                                        true,
                                        "password", 15000.0, null);
                        Bank bank4 = new Bank("Citibank", "388 Greenwich St, New York, NY 10013", SystemUserType.BANK,
                                        true,
                                        "password", 20000.0, null);
                        Bank bank5 = new Bank("Capital One", "1680 Capital One Dr, McLean, VA 22102",
                                        SystemUserType.BANK, true,
                                        "password", 25000.0, null);

                        bankRepository.saveAll(Arrays.asList(bank1, bank2, bank3, bank4, bank5));
                        System.out.println("Bancos inicializados com sucesso!");
                }
        }

}
