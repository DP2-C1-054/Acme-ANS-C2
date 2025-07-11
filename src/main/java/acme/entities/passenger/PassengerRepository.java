
package acme.entities.passenger;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface PassengerRepository extends AbstractRepository {

	@Query("SELECT p FROM Passenger p")
	List<Passenger> findAllPassengers();

	@Query("SELECT p FROM Passenger p WHERE p.passport = :passport AND p.customer.id = :customerId")
	List<Passenger> findCustomerPassengerWithPassport(String passport, int customerId);
}
