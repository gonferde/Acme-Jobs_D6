
package acme.features.consumer.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}
		request.unbind(entity, model, "title", "deadline", "text", "minReward", "maxReward", "ticker");
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;
		result = new Offer();
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		boolean confirmed = request.getModel().getBoolean("confirm");
		errors.state(request, confirmed, "confirm", "consumer.offer.error.confirmed");
		boolean duplicated = this.repository.findOneByTicker(entity.getTicker()) != null;
		errors.state(request, !duplicated, "ticker", "consumer.offer.error.duplicated");
		if (!errors.hasErrors("minReward")) {
			boolean currency = entity.getMinReward().getCurrency().equals("EUR");
			errors.state(request, currency, "minReward", "consumer.offer.error.currency");
			boolean amount = entity.getMinReward().getAmount() > 0;
			errors.state(request, amount, "minReward", "consumer.offer.error.amount");
		}
		if (!errors.hasErrors("maxReward")) {
			boolean currency = entity.getMaxReward().getCurrency().equals("EUR");
			errors.state(request, currency, "maxReward", "consumer.offer.error.currency");
			boolean amount = entity.getMaxReward().getAmount() > 0;
			errors.state(request, amount, "maxReward", "consumer.offer.error.amount");
		}
		if (!errors.hasErrors("minReward") && !errors.hasErrors("maxReward")) {
			boolean bigger = entity.getMaxReward().getAmount() > entity.getMinReward().getAmount();
			errors.state(request, bigger, "minReward", "consumer.offer.error.bigger");
			errors.state(request, bigger, "maxReward", "consumer.offer.error.bigger");
		}
		if (!errors.hasErrors("deadline")) {
			Calendar calendar = new GregorianCalendar();
			boolean deadline = entity.getDeadline().after(calendar.getTime());
			errors.state(request, deadline, "deadline", "consumer.offer.error.deadline");
		}
	}
	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
