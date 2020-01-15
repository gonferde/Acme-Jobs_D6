
package acme.features.employer.job;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		boolean result;
		Job job = this.repository.findOneJobById(request.getModel().getInteger("id"));
		result = job.getEmployer().getUserAccount().getId() == request.getPrincipal().getAccountId();
		return result;
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
		Job result = this.repository.findOneJobById(request.getModel().getInteger("id"));
		if (request.getModel().getString("status") == "DRAFT") {
			result.setStatus(Status.DRAFT);
		} else if (request.getModel().getString("status") == "PUBLISHED") {
			result.setStatus(Status.PUBLISHED);
		}
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("referenceNumber")) {
			if (!this.repository.findOneJobById(entity.getId()).getReferenceNumber().equals(entity.getReferenceNumber())) {
				boolean isUnique = this.repository.findOneJobByReferenceNumber(request.getModel().getString("reference")) != null;
				errors.state(request, !isUnique, "reference", "employer.job.error.label.unique");
			}
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
		if (!errors.hasErrors("status")) {
			boolean isPublished = request.getModel().getString("status").equals("PUBLISHED");
			if (this.repository.findDutiesByDescriptorId(entity.getDescriptor().getId()).size() > 0) {
				if (this.repository.findSumPercentageTimeByDescriptorId(entity.getDescriptor().getId()) < 100) {
					entity.setStatus(Status.DRAFT);
					errors.state(request, !isPublished, "status", "employer.job.error.label.percentageTime");
				}
			} else {
				entity.setStatus(Status.DRAFT);
				errors.state(request, !isPublished, "status", "employer.job.error.label.percentageTime");
			}
			if (!errors.hasErrors("referenceNumber") && !errors.hasErrors("title") && !errors.hasErrors("descriptor.description") && isPublished) {
				CustomisationParameter cp = this.repository.findCustomisationParameters();
				String[] listaCustomisationParameter;
				Integer cuentaTitle = 0;
				Integer cuentaDesc = 0;
				Double limitePalabrasSpamPermitidasTitle = Double.valueOf(entity.getTitle().split(" ").length) * cp.getSpamThreshold() / 100.0;
				Double limitePalabrasSpamPermitidasDescription = Double.valueOf(entity.getDescriptor().getDescription().split(" ").length) * cp.getSpamThreshold() / 100.0;
				listaCustomisationParameter = cp.getSpamWords().split(",");
				for (String s : listaCustomisationParameter) {
					String mensajeParcialTitle = entity.getTitle().toLowerCase();
					String mensajeParcialDescription = entity.getDescriptor().getDescription().toLowerCase();
					int indiceT = mensajeParcialTitle.indexOf(s);
					int indiceD = mensajeParcialDescription.indexOf(s);
					while (indiceT != -1) {
						cuentaTitle++;
						mensajeParcialTitle = mensajeParcialTitle.substring(indiceT + 1);
						indiceT = mensajeParcialTitle.indexOf(s);
					}
					while (indiceD != -1) {
						cuentaDesc++;
						mensajeParcialDescription = mensajeParcialDescription.substring(indiceD + 1);
						indiceD = mensajeParcialDescription.indexOf(s);
					}
					errors.state(request, cuentaTitle <= limitePalabrasSpamPermitidasTitle, "title", "employer.job.error.spam");
					errors.state(request, cuentaDesc <= limitePalabrasSpamPermitidasDescription, "descriptor.description", "employer.job.error.spam");
					if (cuentaTitle > limitePalabrasSpamPermitidasTitle || cuentaDesc > limitePalabrasSpamPermitidasDescription) {
						break;
					}
				}
			}
		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		Descriptor descriptor = entity.getDescriptor();
		descriptor.setDescription(request.getModel().getString("descriptor.description"));
		entity.setDescriptor(descriptor);
		this.repository.save(entity);
	}

}
