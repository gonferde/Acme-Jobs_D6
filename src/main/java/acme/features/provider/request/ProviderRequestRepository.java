
package acme.features.provider.request;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Request;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ProviderRequestRepository extends AbstractRepository {

	@Query("SELECT r FROM Request r WHERE r.ticker = ?1")
	Request findOnebyTicker(String ticker);

}
