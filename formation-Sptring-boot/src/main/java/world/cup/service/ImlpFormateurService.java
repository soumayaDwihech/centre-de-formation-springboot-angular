package world.cup.service;

import world.cup.models.Formateur;
import world.cup.payload.response.*;
import world.cup.repositories.FormateurRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImlpFormateurService implements  IFormateurService{

    @Autowired
    FormateurRepository repo;
    @Override
    public Formateur save(Formateur formateur) {
        return repo.save(formateur);
    }

    @Override
    public void delete(Formateur formateur) {
        repo.delete(formateur);
    }

    @Override
    public Formateur findById(Long id) {
        return repo.findById(id).orElseThrow(null);
    }

	public List<Formateur> findAll() {
		return repo.findAll();
	}

	@Transactional
    public MessageResponse delete(Long id) {
        Formateur formateur = findById(id);
        if (formateur==null){
            return new MessageResponse("Echec");
        }
        repo.delete(formateur);
        return new MessageResponse("Succès");
    }
}