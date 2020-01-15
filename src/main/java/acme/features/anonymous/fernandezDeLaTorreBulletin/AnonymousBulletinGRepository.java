
package acme.features.anonymous.fernandezDeLaTorreBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.FernandezDeLaTorreBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousBulletinGRepository extends AbstractRepository {

	@Query("select a from FernandezDeLaTorreBulletin a")
	Collection<FernandezDeLaTorreBulletin> findMany();

}
