package backend;

import backend.rest.security_new.model.User;
import backend.rest.security_new.repository.UserRepository;
import backend.rest.security_new.service.UserService;
import backend.rest.security_new.service.impl.UserServiceImpl;
import backend.rest.service.repository.ChargeRepository;
import backend.rest.service.repository.ExpenseItemRepository;
import backend.rest.service.repository.SaleRepository;
import backend.rest.service.repository.WarehouseRepository;
import backend.rest.service.repository.entity.Charge;
import backend.rest.service.repository.entity.ExpenseItem;
import backend.rest.service.repository.entity.Sale;
import backend.rest.service.repository.entity.Warehouse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootApplication
public class BackendMain {
    private static final Logger logger = LoggerFactory.getLogger(BackendMain.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendMain.class, args);
    }

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ExpenseItemRepository expenseItemRepository;
    @Autowired
    private ChargeRepository chargeRepository;

    @Bean
    public CommandLineRunner testDataInit() {
        return args -> {
            chargeRepository.deleteAll();
            expenseItemRepository.deleteAll();
            saleRepository.deleteAll();
            warehouseRepository.deleteAll();

            Warehouse warehouse1 = warehouseRepository.save(new Warehouse("MILK", 10, 25.50));
            Warehouse warehouse2 = warehouseRepository.save(new Warehouse("SUGAR", 5, 50.45));
            Warehouse warehouse3 = warehouseRepository.save(new Warehouse("HELLO", 100, 05.55));
            Warehouse warehouse4 = warehouseRepository.save(new Warehouse("POTATO", 40, 10.25));

            saleRepository.save(new Sale(55.65, 15, LocalDateTime.now(), warehouse1));
            saleRepository.save(new Sale(104.45, 2, LocalDateTime.now(), warehouse2));
            saleRepository.save(new Sale(100.05, 20, LocalDateTime.now(), warehouse3));
            saleRepository.save(new Sale(100.05, 20, LocalDateTime.now(), warehouse4));


            ExpenseItem expenseItem1 = expenseItemRepository.save(new ExpenseItem("RENT"));
            ExpenseItem expenseItem2 = expenseItemRepository.save(new ExpenseItem("LOGISTICS"));
            ExpenseItem expenseItem3 = expenseItemRepository.save(new ExpenseItem("REMONT"));
            ExpenseItem expenseItem4 = expenseItemRepository.save(new ExpenseItem("FOOD"));

            chargeRepository.save(new Charge(200.65, LocalDateTime.now(), expenseItem1));
            chargeRepository.save(new Charge(500.35, LocalDateTime.now(), expenseItem2));
            chargeRepository.save(new Charge(300.35, LocalDateTime.now(), expenseItem3));
            chargeRepository.save(new Charge(490.35, LocalDateTime.now(), expenseItem4));
        };
    }
}
