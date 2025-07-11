
package acme.constraints;

import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.realms.airline_managers.AirlineManager;
import acme.realms.airline_managers.AirlineManagerRepository;

@Validator
public class AirlineManagerValidator extends AbstractValidator<ValidAirlineManager, AirlineManager> {

	@Autowired
	private AirlineManagerRepository repository;


	@Override
	protected void initialise(final ValidAirlineManager annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final AirlineManager airlineManager, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		if (airlineManager == null)
			super.state(context, false, "*", "javax.validation.constraints.NotNull.message");
		else {

			String code = airlineManager.getIdentifierNumber();

			List<AirlineManager> airlineManagers = this.repository.findAllAirlineManagers();
			boolean isUnique = airlineManagers.stream().noneMatch(am -> am.getIdentifierNumber().equals(code) && !am.equals(airlineManager));

			if (!isUnique)
				super.state(context, false, "identifierNumber", "acme.validation.airline-manager.identifier-number.message");

		}

		result = !super.hasErrors(context);

		return result;
	}
}
