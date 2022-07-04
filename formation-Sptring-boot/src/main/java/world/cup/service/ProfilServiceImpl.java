package world.cup.service;

import world.cup.models.Profil;
import world.cup.payload.response.*;
import world.cup.repositories.ProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfilServiceImpl implements ProfilService{

@Autowired
ProfilRepository profilRepository;

    @Transactional
    public MessageResponse save(Profil profil) {
        boolean existe = profilRepository.existsByLibelle(profil.getLibelle());
        if (existe){
            return new MessageResponse("Echec !");
        }
        profilRepository.save(profil);
        return new MessageResponse("Succès");
    }


    @Transactional
    public MessageResponse update(Profil profil) {
        boolean existe = profilRepository.existsByLibelle(profil.getLibelle());
        if (!existe){
            boolean existe1 = profilRepository.existsByLibelle(profil.getLibelle());
            return new MessageResponse("Echec !");

        }
        profilRepository.save(profil);
        return new MessageResponse("Succès");
    }

    @Transactional

    public MessageResponse delete(Long id) {
        Profil profil = findById(id);
        if (profil==null){
            return new MessageResponse("Echec");
        }
        profilRepository.delete(profil);
        return new MessageResponse("Succès");
    }


    public List<Profil> findAll() {

        return profilRepository.findAll();
    }

    public Profil findById(Long id) {
        Profil profil = profilRepository.findById(id).orElse(null);
        return profil;
    }
}
