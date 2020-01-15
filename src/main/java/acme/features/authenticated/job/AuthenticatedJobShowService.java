
package acme.features.authenticated.job;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedJobShowService implements AbstractShowService<Authenticated, Job> {

	@Autowired
	AuthenticatedJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		Job job = this.repository.findOneJobById(request.getModel().getInteger("id"));
		Date deadline = new GregorianCalendar().getTime();
		return job.getDeadline().after(deadline) && job.getStatus() == Status.PUBLISHED;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "referenceNumber", "status", "title", "deadline", "salary", "moreInfo", "descriptor", "descriptor.description");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		return this.repository.findOneJobById(request.getModel().getInteger("id"));
	}

}
