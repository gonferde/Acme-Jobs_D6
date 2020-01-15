
package acme.features.administrator.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}
	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "deadLine");
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "goldGoal", "goldReward", "silverGoal", "silverReward", "bronzeGoal", "bronzeReward");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;

		result = new Challenge();

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("goldReward")) {
			boolean isEur = entity.getGoldReward().getCurrency().equals("EUR");
			errors.state(request, isEur, "goldReward", "administrator.challenge.error.euros");
		}
		if (!errors.hasErrors("silverReward")) {
			boolean isEur = entity.getSilverReward().getCurrency().equals("EUR");
			errors.state(request, isEur, "silverReward", "administrator.challenge.error.euros");
		}
		if (!errors.hasErrors("bronzeReward")) {
			boolean isEur = entity.getBronzeReward().getCurrency().equals("EUR");
			errors.state(request, isEur, "bronzeReward", "administrator.challenge.error.euros");
		}
		if (!errors.hasErrors("goldReward")) {
			boolean negative = entity.getGoldReward().getAmount() > 0;
			errors.state(request, negative, "goldReward", "administrator.challenge.error.negative");
		}
		if (!errors.hasErrors("silverReward")) {
			boolean negative = entity.getSilverReward().getAmount() > 0;
			errors.state(request, negative, "silverReward", "administrator.challenge.error.negative");
		}
		if (!errors.hasErrors("bronzeReward")) {
			boolean negative = entity.getBronzeReward().getAmount() > 0;
			errors.state(request, negative, "bronzeReward", "administrator.challenge.error.negative");
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {

		Date deadLine;

		deadLine = new Date(System.currentTimeMillis() + 1 * 1000 * 60 * 60 * 24 * 7);
		entity.setDeadLine(deadLine);
		this.repository.save(entity);

	}
}
