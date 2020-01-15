
package acme.features.anonymous.fernandezDeLaTorreBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.FernandezDeLaTorreBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousBulletinGCreateService implements AbstractCreateService<Anonymous, FernandezDeLaTorreBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousBulletinGRepository repository;


	@Override
	public boolean authorise(final Request<FernandezDeLaTorreBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public FernandezDeLaTorreBulletin instantiate(final Request<FernandezDeLaTorreBulletin> request) {
		assert request != null;

		FernandezDeLaTorreBulletin result;

		Date moment = new Date();

		result = new FernandezDeLaTorreBulletin();
		result.setAuthor("Lelele");
		result.setText("lololo");
		result.setLanguage("lululu");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<FernandezDeLaTorreBulletin> request, final FernandezDeLaTorreBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "author", "text", "language");
	}

	@Override
	public void bind(final Request<FernandezDeLaTorreBulletin> request, final FernandezDeLaTorreBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<FernandezDeLaTorreBulletin> request, final FernandezDeLaTorreBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<FernandezDeLaTorreBulletin> request, final FernandezDeLaTorreBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
