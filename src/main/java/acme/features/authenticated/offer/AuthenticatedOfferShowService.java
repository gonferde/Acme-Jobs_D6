
package acme.features.authenticated.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.framework.components.Model;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedOfferShowService implements AbstractShowService<Authenticated, Offer> {

	@Autowired
	private AuthenticatedOfferRepository repository;


	@Override
	public boolean authorise(final acme.framework.components.Request<Offer> request) {
		assert request != null;
		return true;
	}
	@Override
	public void unbind(final acme.framework.components.Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "moment", "deadline", "text", "minReward", "maxReward", "ticker");
	}
	@Override
	public Offer findOne(final acme.framework.components.Request<Offer> request) {
		assert request != null;
		Offer result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.FindOneById(id);
		return result;
	}

}
