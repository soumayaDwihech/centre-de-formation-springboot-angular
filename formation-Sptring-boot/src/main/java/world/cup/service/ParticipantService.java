package world.cup.service;

import java.util.List;

import world.cup.models.Participant;
import world.cup.payload.response.*;


public interface ParticipantService {

	public MessageResponse save(Participant participant); 
    public MessageResponse update(Participant participant);
    public MessageResponse delete(Long id);
    public List<Participant> findAll();
    public  Participant findById(Long id);
}
