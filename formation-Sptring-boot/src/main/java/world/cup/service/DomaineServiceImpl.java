package world.cup.service;

import world.cup.models.Domaine;
import world.cup.payload.response.*;
import world.cup.repositories.DomaineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DomaineServiceImpl implements DomaineService{

@Autowired
DomaineRepository domaineRepository;

    @Transactional
    @Override
    public MessageResponse save(Domaine domaine) {
        boolean existe = domaineRepository.existsByLibelle(domaine.getLibelle());
        if (existe){
            return new MessageResponse("Echec !");
        }
        domaineRepository.save(domaine);
        return new MessageResponse("Succès");
    }


    @Transactional
    @Override
    public MessageResponse update(Domaine domaine) {
        boolean existe = domaineRepository.existsById(domaine.getId());
        if (!existe){
            boolean existe1 = domaineRepository.existsByLibelle(domaine.getLibelle());
            return new MessageResponse("Echec !");

        }
        domaineRepository.save(domaine);
        return new MessageResponse("Succès");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Domaine domaine = findById(id);
        if (domaine==null){
            return new MessageResponse("Echec");
        }
        domaineRepository.delete(domaine);
        return new MessageResponse("Succès");
    }

    @Override
    public List<Domaine> findAll() {

        return domaineRepository.findAll();
    }

    @Override
    public Domaine findById(Long id) {
        Domaine domaine = domaineRepository.findById(id).orElse(null);
        return domaine;
    }
}
