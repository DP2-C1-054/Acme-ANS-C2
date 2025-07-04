
package acme.features.administrator.airport;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.principals.Administrator;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.airport.Airport;
import acme.entities.airport.AirportType;

@GuiService
public class AdministratorAirportCreateService extends AbstractGuiService<Administrator, Airport> {

	@Autowired
	private AdministratorAirportRepository repository;


	@Override
	public void authorise() {
		boolean status = true;

		if (super.getRequest().getMethod().equals("POST"))
			if (status && super.getRequest().hasData("operationalScope")) {
				String scope = super.getRequest().getData("operationalScope", String.class);
				status = scope.equals("0") || scope.equals("INTERNATIONAL") || scope.equals("DOMESTIC") || scope.equals("REGIONAL");
			}

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		Airport airport;

		airport = new Airport();

		super.getBuffer().addData(airport);
	}

	@Override
	public void bind(final Airport airport) {
		super.bindObject(airport, "name", "iataCode", "operationalScope", "city", "country", "email", "contactPhone", "website");
	}

	@Override
	public void validate(final Airport airport) {
		boolean confirmation;
		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "acme.validation.confirmation.message");
	}

	@Override
	public void perform(final Airport airport) {
		this.repository.save(airport);
	}

	@Override
	public void unbind(final Airport airport) {
		Dataset dataset;
		SelectChoices choices;

		choices = SelectChoices.from(AirportType.class, airport.getOperationalScope());
		dataset = super.unbindObject(airport, "name", "iataCode", "operationalScope", "city", "country", "email", "contactPhone", "website");
		dataset.put("types", choices);

		super.getResponse().addData(dataset);
	}

}
