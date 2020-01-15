
package acme.features.authenticated.offer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.framework.components.Model;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedOfferListService implements AbstractListService<Authenticated, Offer> {

	@Autowired
	AuthenticatedOfferRepository repository;


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
	public Collection<Offer> findMany(final acme.framework.components.Request<Offer> request) {
		assert request != null;
		Collection<Offer> result;
		result = this.repository.findManyAll();
		return result;
	}

}
