
package acme.forms.airline_manager;

import java.util.List;

import acme.client.components.basis.AbstractForm;
import acme.entities.airport.Airport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirlineManagerDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	Integer						rankingManagerByExperience;
	Integer						yearsToRetire;
	Double						ratioOnTimeLegs;
	Double						ratioDelayedLegs;
	Airport						mostPopular;
	Airport						lessPopular;
	List<LegsByStatus>			numberLegsByStatus;
	FlightStatistics			statisticsAboutFlights;

}
