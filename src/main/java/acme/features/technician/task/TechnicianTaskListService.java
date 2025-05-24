
package acme.features.technician.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.maintenance.MaintenanceRecord;
import acme.entities.tasks.Task;
import acme.realms.technicians.Technician;

@GuiService
public class TechnicianTaskListService extends AbstractGuiService<Technician, Task> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private TechnicianTaskRepository repository;


	// AbstractGuiService interface -------------------------------------------
	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<Task> tasks;
		int maintenanceRecordId;
		boolean draftMode;
		MaintenanceRecord maintenanceRecord;
		boolean showCreate;
		int technicianId = super.getRequest().getPrincipal().getActiveRealm().getId();

		if (super.getRequest().getData().isEmpty())
			tasks = this.repository.findTasksByTechnicianId(technicianId);
		else {
			maintenanceRecordId = super.getRequest().getData("maintenanceRecordId", int.class);
			super.getResponse().addGlobal("maintenanceRecordId", maintenanceRecordId);

			maintenanceRecord = this.repository.findMaintenanceRecordById(maintenanceRecordId);
			super.getResponse().addGlobal("maintenanceRecordId", maintenanceRecordId);

			showCreate = maintenanceRecord.isDraftMode() && super.getRequest().getPrincipal().hasRealm(maintenanceRecord.getTechnician());
			super.getResponse().addGlobal("showCreate", showCreate);
			if (super.getRequest().hasData("draftMode")) {
				draftMode = super.getRequest().getData("draftMode", boolean.class);
				super.getResponse().addGlobal("draftMode", draftMode);
			}
			tasks = this.repository.findInvolvesByMaintenanceRecord(maintenanceRecord);
		}
		super.getBuffer().addData(tasks);
	}

	@Override
	public void unbind(final Task task) {
		Dataset dataset;

		dataset = super.unbindObject(task, "type", "priority", "estimatedDuration");

		super.getResponse().addData(dataset);

	}
}
