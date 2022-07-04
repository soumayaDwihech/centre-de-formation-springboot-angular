package world.cup.service;

import world.cup.models.Formation;
import world.cup.payload.response.*;
import world.cup.repositories.FormationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImplFormationService implements IFormationService{


    @Autowired
    FormationRepository repo;
    @Override
    public Formation save(Formation formation) {
        return repo.save(formation);
    }

    @Override
    public void delete(Formation formation) {
        repo.delete(formation);
    }

    @Override
    public Formation findById(Long id) {
        return repo.findById(id).orElseThrow(null);
    }

	public List<Formation> findAll() {
		 return repo.findAll();
	}

	@Transactional
    public MessageResponse delete(Long id) {
        Formation formation = findById(id);
        if (formation==null){
            return new MessageResponse("Echec");
        }
        repo.delete(formation);
        return new MessageResponse("Succès");
    }
}