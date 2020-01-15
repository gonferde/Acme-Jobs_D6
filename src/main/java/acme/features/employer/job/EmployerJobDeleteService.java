
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return this.repository.findOneJobById(request.getModel().getInteger("id")).getEmployer().getUserAccount().getId() == request.getPrincipal().getAccountId() && this.repository.findApplicationsByJobId(request.getModel().getInteger("id")).size() < 1;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
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

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		this.repository.deleteAll(this.repository.findDutiesByDescriptorId(entity.getDescriptor().getId()));
		this.repository.delete(entity);
	}

}
