
package acme.features.employer.job;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	private EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
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
	public Job instantiate(final Request<Job> request) {
		assert request != null;
		Job result = new Job();
		result.setEmployer(this.repository.findOneEmployerByEmployerId(request.getPrincipal().getActiveRoleId()));
		result.setStatus(Status.DRAFT);
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("referenceNumber")) {
			boolean isUnique = this.repository.findOneJobByReferenceNumber(entity.getReferenceNumber()) != null;
			errors.state(request, !isUnique, "referenceNumber", "employer.job.error.label.unique");
		}
		if (!errors.hasErrors("deadline")) {
			errors.state(request, entity.getDeadline().after(new GregorianCalendar().getTime()), "deadline", "employer.job.error.label.deadline");
		}
		if (!errors.hasErrors("salary")) {
			boolean isEuro = entity.getSalary().getCurrency().equals("EUR") || entity.getSalary().getCurrency().equals("€");
			errors.state(request, isEuro, "salary", "employer.job.error.label.salary-currency");
			boolean positiveSalary = entity.getSalary().getAmount() >= 0;
			errors.state(request, positiveSalary, "salary", "employer.job.error.label.salary-amount");
		}
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		Descriptor descriptor = new Descriptor();
		descriptor.setDescription(request.getModel().getString("descriptor.description"));
		entity.setDescriptor(descriptor);
		this.repository.save(entity);
	}

}
