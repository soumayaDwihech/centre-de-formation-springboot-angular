package world.cup.service;

import world.cup.models.Profil;
import world.cup.payload.response.*;

import java.util.List;

public interface ProfilService {
    public MessageResponse save(Profil profil);
    public MessageResponse update(Profil profil);
    public MessageResponse delete(Long id);
    public List<Profil> findAll();
    public Profil findById(Long id);
}
