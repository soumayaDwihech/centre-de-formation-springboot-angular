package world.cup.repositories;

import world.cup.models.Organisme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganismeRepository extends JpaRepository<Organisme, Long> {

    static boolean existsByid(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}