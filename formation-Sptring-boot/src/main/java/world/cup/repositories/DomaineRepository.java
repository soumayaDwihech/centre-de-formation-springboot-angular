package world.cup.repositories;

import world.cup.models.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomaineRepository extends JpaRepository<Domaine, Long> {

    boolean existsByLibelle(String libelle);
}
