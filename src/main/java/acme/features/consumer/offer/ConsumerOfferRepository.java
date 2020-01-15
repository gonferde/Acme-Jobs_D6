
package acme.features.consumer.offer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.offers.Offer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ConsumerOfferRepository extends AbstractRepository {

	@Query("SELECT o FROM Offer o WHERE o.ticker = ?1")
	Offer findOneByTicker(String ticker);

}
