package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import world.cup.models.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Long> {

	boolean existsByEmail(String email);
}
