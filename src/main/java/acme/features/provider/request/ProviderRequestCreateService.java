
package acme.features.provider.request;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestCreateService implements AbstractCreateService<Provider, Request> {

	@Autowired
	ProviderRequestRepository repository;


	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final acme.framework.components.Request<Request> request, final Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}
		request.unbind(entity, model, "title", "deadline", "text", "reward", "ticker");
	}

	@Override
	public Request instantiate(final acme.framework.components.Request<Request> request) {
		assert request != null;
		Request result = new Request();
		return result;
	}

	@Override
	public void validate(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		boolean confirmed = request.getModel().getBoolean("confirm");
		errors.state(request, confirmed, "confirm", "provider.request.error.confirmed");
		boolean duplicated = this.repository.findOnebyTicker(entity.getTicker()) != null;
		errors.state(request, !duplicated, "ticker", "provider.request.error.duplicated");
		if (!errors.hasErrors("reward")) {
			boolean currency = entity.getReward().getCurrency().equals("EUR");
			errors.state(request, currency, "reward", "provider.request.error.currency");
			boolean amount = entity.getReward().getAmount() > 0;
			errors.state(request, amount, "reward", "provider.request.error.amount");
		}
		if (!errors.hasErrors("deadline")) {
			Calendar calendar = new GregorianCalendar();
			boolean deadline = entity.getDeadline().after(calendar.getTime());
			errors.state(request, deadline, "deadline", "provider.request.error.deadline");
		}
	}

	@Override
	public void create(final acme.framework.components.Request<Request> request, final Request entity) {
		assert request != null;
		assert entity != null;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
