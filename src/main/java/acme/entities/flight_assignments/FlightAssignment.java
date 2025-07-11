
package acme.entities.flight_assignments;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidString;
import acme.constraints.ValidFlightAssignment;
import acme.entities.legs.Leg;
import acme.realms.flight_crew_members.FlightCrewMember;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidFlightAssignment
@Table(indexes = {
	@Index(columnList = "allocated_flight_crew_member_id"), @Index(columnList = "allocated_flight_crew_member_id, draftMode, status")
})

public class FlightAssignment extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private FlightCrewMember	allocatedFlightCrewMember;

	@Mandatory
	@Valid
	@Automapped
	private Duty				duty;

	@Mandatory
	@ValidMoment(past = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@Mandatory
	@Valid
	@Automapped
	private AssignmentStatus	status;

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Leg					leg;

	@Optional
	@ValidString
	@Automapped
	private String				remarks;

	@Mandatory
	@Automapped
	private boolean				draftMode;

}
