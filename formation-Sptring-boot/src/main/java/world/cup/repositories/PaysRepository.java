package world.cup.repositories;

import world.cup.models.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {

    static boolean existsByid(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}