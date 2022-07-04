package world.cup.service;

import world.cup.models.Organisme;
import world.cup.payload.response.*;
import world.cup.repositories.OrganismeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganismeServiceImpl implements OrganismeService{

@Autowired
OrganismeRepository organismeRepository;

    @Transactional
    @Override
    public MessageResponse save(Organisme organisme) {
        boolean existe = OrganismeRepository.existsByid(organisme.getId());
        if (existe){
            return new MessageResponse("Echec !");
        }
        organismeRepository.save(organisme);
        return new MessageResponse("Succès");
    }


    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Organisme organisme = findById(id);
        if (organisme==null){
            return new MessageResponse("Echec");
        }
        organismeRepository.delete(organisme);
        return new MessageResponse("Succès");
    }

    @Override
    public List<Organisme> findAll() {
        return organismeRepository.findAll();
    }

    @Override
    public Organisme findById(Long id) {
        Organisme organisme = organismeRepository.findById(id).orElse(null);
        return organisme;
    }


    @Transactional
    @Override
    public MessageResponse update(Organisme organisme) {
        boolean existe = organismeRepository.existsById(organisme.getId());
        if (!existe){
            boolean existe1 = OrganismeRepository.existsByid(organisme.getId());
            return new MessageResponse("Echec !");

        }
        organismeRepository.save(organisme);
        return new MessageResponse("Succès");
    }
}