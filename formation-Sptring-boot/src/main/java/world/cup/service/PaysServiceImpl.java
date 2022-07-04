package world.cup.service;

import world.cup.models.Pays;
import world.cup.payload.response.*;
import world.cup.repositories.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaysServiceImpl implements PaysService{

@Autowired
PaysRepository paysRepository;

    @Transactional
    @Override
    public MessageResponse save(Pays pays) {
        boolean existe = PaysRepository.existsByid(pays.getId());
        if (existe){
            return new MessageResponse("Echec !");
        }
        paysRepository.save(pays);
        return new MessageResponse("Succès");
    }


    @Transactional
    @Override
    public MessageResponse update(Pays pays) {
        boolean existe = paysRepository.existsById(pays.getId());
        if (!existe){
            boolean existe1 = PaysRepository.existsByid(pays.getId());
            return new MessageResponse("Echec !");

        }
        paysRepository.save(pays);
        return new MessageResponse("Succès");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Pays pays = findById(id);
        if (pays==null){
            return new MessageResponse("Echec");
        }
        paysRepository.delete(pays);
        return new MessageResponse("Succès");
    }

    @Override
    public List<Pays> findAll() {

        return paysRepository.findAll();
    }

    @Override
    public Pays findById(Long id) {
        Pays pays = paysRepository.findById(id).orElse(null);
        return pays;
    }
}
