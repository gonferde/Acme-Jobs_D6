
package acme.features.authenticated.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedJobRepository extends AbstractRepository {

	@Query("SELECT j FROM Job j WHERE j.id=?1")
	Job findOneJobById(int id);

	@Query("SELECT j FROM Job j WHERE j.deadline>current_date() AND j.status=1")
	Collection<Job> findManyActive();

}
