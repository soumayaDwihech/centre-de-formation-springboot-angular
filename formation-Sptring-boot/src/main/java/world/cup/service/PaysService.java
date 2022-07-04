package world.cup.service;

import world.cup.models.Pays;
import world.cup.payload.response.*;

import java.util.List;

public interface PaysService {
    public MessageResponse save(Pays pays);
    public MessageResponse update(Pays pays);
    public MessageResponse delete(Long id);
    public List<Pays> findAll();
    public Pays findById(Long id);
}
