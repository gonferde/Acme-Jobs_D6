
package acme.features.anonymous.fernandezDeLaTorreBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.FernandezDeLaTorreBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/fernandez-de-la-torre-bulletin/")
public class AnonymousBulletinGController extends AbstractController<Anonymous, FernandezDeLaTorreBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousBulletinGListService	listService;

	@Autowired
	private AnonymousBulletinGCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
