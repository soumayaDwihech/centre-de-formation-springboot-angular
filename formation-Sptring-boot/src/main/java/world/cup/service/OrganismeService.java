package world.cup.service;

import world.cup.models.Organisme;
import world.cup.payload.response.*;

import java.util.List;

public interface OrganismeService {
    public MessageResponse save(Organisme organisme);
    public MessageResponse update(Organisme organisme);
    public MessageResponse delete(Long id);
    public List<Organisme> findAll();
    public Organisme findById(Long id);
}
