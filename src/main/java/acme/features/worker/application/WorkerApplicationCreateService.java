
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	private WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		int idJob = request.getModel().getInteger("idJob");
		Job job = this.repository.findJobById(idJob);
		boolean result = job.getDeadline().after(new Date()) && job.getStatus().equals(acme.entities.jobs.Status.PUBLISHED);

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "worker", "job");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "statement", "skills", "qualifications");

		int jobId;
		jobId = request.getModel().getInteger("idJob");
		model.setAttribute("idJob", jobId);
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;
		int jobId;
		int workerId;
		Job job;
		Worker worker;

		jobId = request.getModel().getInteger("idJob");
		job = this.repository.findJobById(jobId);

		workerId = request.getPrincipal().getActiveRoleId();
		worker = this.repository.findWorkerById(workerId);

		result = new Application();
		result.setJob(job);
		result.setWorker(worker);
		result.setStatus(acme.entities.applications.Status.PENDING);
		result.setCreationMoment(new Date());

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("referenceNumber")) {
			boolean isUnique = this.repository.findOneByReferenceNumber(entity.getReferenceNumber()) != null;
			errors.state(request, !isUnique, "referenceNumber", "worker.application.error.label.unique");
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);
	}

}
