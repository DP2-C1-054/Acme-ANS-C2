
package acme.features.technician.task;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.tasks.Task;
import acme.entities.tasks.TaskType;
import acme.realms.technicians.Technician;

@GuiService
public class TechnicianTaskPublishService extends AbstractGuiService<Technician, Task> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private TechnicianTaskRepository repository;

	// AbstractGuiService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int taskId;
		Task task;
		Technician technician;

		taskId = super.getRequest().getData("id", int.class);
		task = this.repository.findTaskById(taskId);
		technician = task == null ? null : task.getTechnician();
		status = task != null && task.isDraftMode() && super.getRequest().getPrincipal().hasRealm(technician);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Task task;
		int id;

		id = super.getRequest().getData("id", int.class);
		task = this.repository.findTaskById(id);

		super.getBuffer().addData(task);
	}

	@Override
	public void bind(final Task task) {
		Technician technician = (Technician) super.getRequest().getPrincipal().getActiveRealm();
		super.bindObject(task, "type", "description", "priority", "estimatedDuration");
		task.setTechnician(technician);
	}
	@Override
	public void validate(final Task task) {
		;
	}
	@Override
	public void perform(final Task task) {
		task.setDraftMode(false);
		this.repository.save(task);
	}
	@Override
	public void unbind(final Task task) {
		Dataset dataset;
		SelectChoices choices;

		choices = SelectChoices.from(TaskType.class, task.getType());

		dataset = super.unbindObject(task, "type", "description", "priority", "estimatedDuration", "draftMode");
		dataset.put("technician", task.getTechnician().getIdentity().getFullName());
		dataset.put("types", choices);
		dataset.put("type", choices.getSelected().getKey());
		dataset.put("readonly", false);

		super.getResponse().addData(dataset);
	}
}
