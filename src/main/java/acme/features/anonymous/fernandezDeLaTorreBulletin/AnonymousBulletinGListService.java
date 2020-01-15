
package acme.features.anonymous.fernandezDeLaTorreBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.FernandezDeLaTorreBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousBulletinGListService implements AbstractListService<Anonymous, FernandezDeLaTorreBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousBulletinGRepository repository;


	@Override
	public boolean authorise(final Request<FernandezDeLaTorreBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<FernandezDeLaTorreBulletin> findMany(final Request<FernandezDeLaTorreBulletin> request) {
		assert request != null;
		Collection<FernandezDeLaTorreBulletin> result;
		result = this.repository.findMany();
		return result;
	}

	@Override
	public void unbind(final Request<FernandezDeLaTorreBulletin> request, final FernandezDeLaTorreBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "moment", "author", "text", "language");
	}

}
