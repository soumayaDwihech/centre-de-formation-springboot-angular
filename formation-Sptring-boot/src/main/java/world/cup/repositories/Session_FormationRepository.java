package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import world.cup.models.Session_formation;

@Repository
public interface Session_FormationRepository extends JpaRepository<Session_formation, Long>  {
	
}