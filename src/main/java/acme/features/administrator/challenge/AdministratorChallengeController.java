
package acme.features.administrator.challenge;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.challenges.Challenge;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/challenge/")
public class AdministratorChallengeController extends AbstractController<Administrator, Challenge> {

	@Autowired
	private AdministratorChallengeListService	listService;

	@Autowired
	private AdministratorChallengeShowService	showService;

	@Autowired
	private AdministratorChallengeCreateService	createService;

	@Autowired
	private AdministratorChallengeUpdateService	updateService;

	@Autowired
	private AdministratorChallengeDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}

// DeadLine se considera moment_? es decir, tiene que ser generado automaticamente por el sistema?
// En el update se tiene que modificar el moment?? por ejemplo ampliar la fecha limite?
// Currency no me vale como constraint validator
