
package acme.features.authenticated.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedDutyRepository extends AbstractRepository {

	@Query("SELECT j FROM Job j WHERE j.descriptor.id=?1")
	Job findJobByDescriptorId(int descriptorId);

	@Query("SELECT d FROM Duty d WHERE d.descriptor.id=?1")
	Collection<Duty> findManyByDescriptorId(int descriptorId);

	@Query("SELECT d FROM Duty d WHERE d.id=?1")
	Duty findOneById(int id);

	@Query("SELECT j FROM Job j WHERE j.descriptor.id=(SELECT d.descriptor.id FROM Duty d WHERE d.id=?1)")
	Job findJobById(int id);

}
