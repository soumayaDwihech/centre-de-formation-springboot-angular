package world.cup.repositories;

import world.cup.models.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {

    boolean existsByLibelle(String libelle);
}
