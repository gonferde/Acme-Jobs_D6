
package acme.features.anonymous.porteroMontanoBulletin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PorteroMontanoBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousPorteroMontanoBulletinCreateService implements AbstractCreateService<Anonymous, PorteroMontanoBulletin> {

	//  Internal state-------------------------------------------------------

	@Autowired
	AnonymousPorteroMontanoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PorteroMontanoBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public PorteroMontanoBulletin instantiate(final Request<PorteroMontanoBulletin> request) {
		assert request != null;

		PorteroMontanoBulletin result;
		Date dateOfExpiry;

		dateOfExpiry = new GregorianCalendar(2019, Calendar.DECEMBER, 10).getTime();

		result = new PorteroMontanoBulletin();
		result.setProduct("avocado");
		result.setDescription("Strange");
		result.setDateOfExpiry(dateOfExpiry);

		return result;
	}

	@Override
	public void unbind(final Request<PorteroMontanoBulletin> request, final PorteroMontanoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "product", "description", "dateOfExpiry");
	}

	@Override
	public void bind(final Request<PorteroMontanoBulletin> request, final PorteroMontanoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<PorteroMontanoBulletin> request, final PorteroMontanoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<PorteroMontanoBulletin> request, final PorteroMontanoBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
