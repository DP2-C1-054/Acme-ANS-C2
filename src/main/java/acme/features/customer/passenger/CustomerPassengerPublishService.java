
package acme.features.customer.passenger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.passenger.Passenger;
import acme.entities.passenger.Takes;
import acme.realms.customer.Customer;

@GuiService
public class CustomerPassengerPublishService extends AbstractGuiService<Customer, Passenger> {

	@Autowired
	private CustomerPassengerRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Passenger passenger;

		masterId = super.getRequest().getData("id", int.class);
		passenger = this.repository.findPassengerById(masterId);
		status = passenger != null && passenger.isDraftMode() && super.getRequest().getPrincipal().hasRealm(passenger.getCustomer());
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Passenger passenger;
		int id;

		id = super.getRequest().getData("id", int.class);
		passenger = this.repository.findPassengerById(id);

		super.getBuffer().addData(passenger);
	}
	@Override
	public void bind(final Passenger passenger) {
		super.bindObject(passenger, "name", "mail", "passport", "birthDate", "specialNeeds");
	}

	@Override
	public void validate(final Passenger passenger) {
		;
	}

	@Override
	public void perform(final Passenger passenger) {
		passenger.setDraftMode(false);
		this.repository.save(passenger);
	}

	@Override
	public void unbind(final Passenger passenger) {
		Dataset dataset;
		Boolean showDelete;
		int masterId;
		List<Takes> takes;
		masterId = super.getRequest().getData("id", int.class);
		takes = this.repository.findTakesByPassengerId(masterId);
		showDelete = takes.isEmpty();

		dataset = super.unbindObject(passenger, "name", "mail", "passport", "birthDate", "specialNeeds", "draftMode");
		dataset.put("showDelete", showDelete);
		super.getResponse().addData(dataset);
	}
}
