
package acme.features.authenticated.offer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.offers.Offer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedOfferRepository extends AbstractRepository {

	@Query("SELECT o FROM Offer o WHERE o.id=?1")
	Offer FindOneById(int id);

	@Query("SELECT o FROM Offer o WHERE o.deadline>current_date()")
	Collection<Offer> findManyAll();

}
