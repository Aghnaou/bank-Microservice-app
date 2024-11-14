package net.mohamed.customer_service.repositories;

import net.mohamed.customer_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
