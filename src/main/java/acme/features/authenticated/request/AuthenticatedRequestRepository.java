
package acme.features.authenticated.request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Request;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestRepository extends AbstractRepository {

	@Query("SELECT r FROM Request r WHERE r.id=?1")
	Request FindOneById(int id);

	@Query("SELECT r FROM Request r WHERE r.deadline>current_date()")
	Collection<Request> findManyAll();

}
