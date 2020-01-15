
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("SELECT d FROM Duty d WHERE d.descriptor.id=?1")
	Collection<Duty> findManyByDescriptorId(int descriptorId);

	@Query("SELECT d FROM Duty d WHERE d.id=?1")
	Duty findOneById(int id);

	@Query("SELECT j FROM Job j WHERE j.descriptor.id=(SELECT d.descriptor.id FROM Duty d WHERE d.id=?1)")
	Job findJobById(int id);

	@Query("SELECT j.employer.id FROM Job j WHERE j.descriptor.id=?1")
	Integer findEmployerIdByDescriptorId(Integer descriptorId);

	@Query("SELECT j.status FROM Job j WHERE j.descriptor.id=?1")
	Status findJobStatusByDescriptorId(int descriptorId);

	@Query("SELECT d FROM Descriptor d WHERE d.id=?1")
	Descriptor findDescriptorByDescriptorId(int descriptorId);

	@Query("SELECT sum (d.percentageTime) FROM Duty d WHERE d.descriptor.id=?1")
	int findSumPercentageTimeByDescriptorId(int descriptorId);

	@Query("SELECT j.employer.id FROM Job j WHERE j.descriptor.id=(SELECT d.descriptor.id FROM Duty d WHERE d.id=?1)")
	Integer findEmployerIdByDutyId(int dutyId);

}
