
package acme.features.administrator.trackingLog;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.principals.Administrator;
import acme.client.controllers.AbstractGuiController;
import acme.client.controllers.GuiController;
import acme.entities.tracking_logs.TrackingLog;

@GuiController
public class AdministratorTrackingLogController extends AbstractGuiController<Administrator, TrackingLog> {

	@Autowired
	private AdministratorTrackingLogListService	listService;

	@Autowired
	private AdministratorTrackingLogShowService	showService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}

}
