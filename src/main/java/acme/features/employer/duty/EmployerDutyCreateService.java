
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		Integer descriptorId = request.getModel().getInteger("id");
		return request.getPrincipal().getActiveRoleId() == this.repository.findEmployerIdByDescriptorId(descriptorId) && this.repository.findJobStatusByDescriptorId(descriptorId) == Status.DRAFT;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "description", "percentageTime", "descriptor", "descriptor.id");
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		assert request != null;
		Duty result = new Duty();
		result.setDescriptor(this.repository.findDescriptorByDescriptorId(request.getModel().getInteger("id")));
		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		int descriptorId = request.getModel().getInteger("id");
		if (!errors.hasErrors("percentageTime")) {
			int modelAmountTime = request.getModel().getInteger("percentageTime");
			int sumOfAmountTimes;
			if (this.repository.findManyByDescriptorId(descriptorId).size() > 0) {
				sumOfAmountTimes = this.repository.findSumPercentageTimeByDescriptorId(descriptorId);
				Boolean condition = modelAmountTime + sumOfAmountTimes <= 100;
				errors.state(request, condition, "percentageTime", "employer.duty.error.label.percentageTime");
			}
		}
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;
		entity.setDescriptor(this.repository.findDescriptorByDescriptorId(request.getModel().getInteger("id")));
		this.repository.save(entity);
	}

}
