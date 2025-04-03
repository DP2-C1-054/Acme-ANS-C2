
package acme.features.technician.maintenance;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.maintenance.MaintenanceRecord;
import acme.realms.technicians.Technician;

@GuiService
public class TechnicianMaintenanceRecordListService extends AbstractGuiService<Technician, MaintenanceRecord> {

	@Autowired
	private TechnicianMaintenanceRecordRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		int technicianId;
		Collection<MaintenanceRecord> mr;

		technicianId = super.getRequest().getPrincipal().getActiveRealm().getId();
		mr = this.repository.findAllMaintenanceRecordByTechnicianId(technicianId);

		super.getBuffer().addData(mr);
	}

	@Override
	public void unbind(final MaintenanceRecord maintenanceRecord) {
		Dataset dataset;

		dataset = super.unbindObject(maintenanceRecord, "aircraft", "status", "maintenanceMoment", "nextInspectionDue");
		super.addPayload(dataset, maintenanceRecord, "aircraft.model", "aircraft.registrationNumber");

		super.getResponse().addData(dataset);
	}

}
