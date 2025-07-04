
package acme.features.assistanceAgent.claim;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.airlines.Airline;
import acme.entities.claims.Claim;
import acme.entities.legs.Leg;
import acme.entities.tracking_logs.TrackingLog;

@Repository
public interface AssistanceAgentClaimRepository extends AbstractRepository {

	@Query("SELECT DISTINCT c FROM Claim c JOIN TrackingLog t ON t.claim.id = c.id WHERE t.percentage = (SELECT MAX(t2.percentage) FROM TrackingLog t2 WHERE t2.claim = c) AND (t.status != 'PENDING' AND c.assistanceAgent.id = :agentId)")
	Collection<Claim> findAllCompletedClaimsByAgentId(int agentId);

	@Query("SELECT DISTINCT c FROM Claim c JOIN TrackingLog t ON t.claim.id = c.id WHERE t.percentage = (SELECT MAX(t2.percentage) FROM TrackingLog t2 WHERE t2.claim = c) AND (t.status = 'PENDING' AND c.assistanceAgent.id = :agentId)")
	Collection<Claim> findAllPendingClaimsByAgentId(int agentId);

	@Query("SELECT c FROM Claim c WHERE c.id NOT IN (SELECT t.claim.id FROM TrackingLog t) AND (c.assistanceAgent.id = :agentId)")
	Collection<Claim> findAllEmptyClaimsByAgentId(int agentId);

	@Query("SELECT c FROM Claim c WHERE c.id = :id")
	Claim findClaimById(int id);

	@Query("SELECT l FROM Leg l")
	Collection<Leg> findAllLegs();

	@Query("SELECT COUNT(tl) FROM TrackingLog tl WHERE tl.claim.id = :claimId")
	int countTrackingLogsByClaimId(int claimId);

	@Query("SELECT tl FROM TrackingLog tl WHERE tl.claim.id = :claimId")
	Collection<TrackingLog> findAllTrackingLogsByClaimId(int claimId);

	@Query("SELECT l FROM Leg l WHERE l.id = :id")
	Leg findLegById(int id);

	@Query("SELECT l from Leg l WHERE l.scheduledArrival < CURRENT_TIMESTAMP and l.draftMode = false and l.aircraft.airline = :agentAirline and l.id = :id")
	Leg findValidLegById(Airline agentAirline, int id);

}
