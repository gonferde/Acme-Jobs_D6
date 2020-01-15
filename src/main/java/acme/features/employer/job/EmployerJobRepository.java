
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("SELECT j FROM Job j WHERE j.id=?1")
	Job findOneJobById(int id);

	@Query("SELECT j FROM Job j WHERE j.employer.id=?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("SELECT e FROM Employer e WHERE e.id=?1")
	Employer findOneEmployerByEmployerId(int employerId);

	@Query("SELECT j FROM Job j WHERE j.referenceNumber=?1")
	Job findOneJobByReferenceNumber(String referenceNumber);

	@Query("SELECT d FROM Duty d WHERE d.descriptor.id=?1")
	Collection<Duty> findDutiesByDescriptorId(int descriptorId);

	@Query("SELECT sum (d.percentageTime) FROM Duty d WHERE d.descriptor.id=?1")
	int findSumPercentageTimeByDescriptorId(int descriptorId);

	@Query("SELECT cp FROM CustomisationParameter cp")
	CustomisationParameter findCustomisationParameters();

	@Query("SELECT a FROM Application a WHERE a.job.id=?1")
	Collection<Application> findApplicationsByJobId(int jobId);

}
