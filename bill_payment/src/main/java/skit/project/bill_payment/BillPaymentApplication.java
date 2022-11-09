package skit.project.bill_payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("skit.project.bill_payment.entity") 
@EnableJpaRepositories("skit.project.bill_payment.repository")
public class BillPaymentApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Application is going to be started");
		SpringApplication.run(BillPaymentApplication.class, args);
		System.out.println("Application Started");
	}

}
