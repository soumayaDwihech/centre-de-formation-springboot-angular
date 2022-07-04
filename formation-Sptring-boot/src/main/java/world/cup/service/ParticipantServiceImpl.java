package world.cup.service;

import world.cup.models.Participant;
import world.cup.payload.response.*;
import world.cup.repositories.ParticipantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParticipantServiceImpl  implements ParticipantService {

@Autowired
ParticipantRepository participantRepository;

    @Transactional
    @Override
    public MessageResponse save(Participant participant) {
    	
    	// test si participant exsite déja ou non 
        boolean existe = participantRepository.existsByEmail(participant.getEmail());
        if (existe){
            return new MessageResponse ("Echec !");
        }
        participantRepository.save(participant);
        return new MessageResponse("Succès");
    }


    @Transactional
    @Override
    public MessageResponse update(Participant participant) {
   
    	boolean existe = participantRepository.existsById(participant.getId());
        if (!existe){
        	boolean existe1 =participantRepository.existsByEmail(participant.getEmail());
            return new MessageResponse ("Echec !");
        }
        participantRepository.save(participant);
        return new MessageResponse("Succès");
    
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Participant participant = findById(id);
        if (participant==null){
            return new MessageResponse("Echec");
        }
        participantRepository.delete(participant);
        return new MessageResponse("Succès");
    }

    @Override
    public List<Participant> findAll() {

        return participantRepository.findAll();
    }

    @Override
    public Participant findById(Long id) {
        Participant participant = participantRepository.findById(id).orElse(null);
        return participant;
    }
}
