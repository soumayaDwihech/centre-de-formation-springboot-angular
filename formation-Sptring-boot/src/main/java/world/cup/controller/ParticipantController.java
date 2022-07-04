package world.cup.controller;

import world.cup.models.Participant;
import world.cup.payload.response.*;
import world.cup.service.ParticipantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@CrossOrigin("*")
@RequestMapping("/Participant")
public class ParticipantController {

    @Autowired
    private ParticipantServiceImpl participantService;

    //@RequestMapping(value="/participant", method=RequestMethod.GET)
    @GetMapping("/allParticipants")
    public List<Participant> findAll() {
        return participantService.findAll();
    }

    @PostMapping("/AddParticipant")
    public MessageResponse save(@RequestBody Participant participant) {
        return participantService.save(participant);
    }

    @PutMapping("/updateParticipant/{id}")
    public MessageResponse updateParticipant(@PathVariable(value = "id") Long Id, @Validated @RequestBody Participant detailsParticipant) {

        Participant participant = participantService.findById(Id);
        participant.setNom(detailsParticipant.getNom());
        participant.setPrenom(detailsParticipant.getPrenom());
        participant.setEmail(detailsParticipant.getEmail());
        participant.setTel(detailsParticipant.getTel());
        participant.setOrganisme(detailsParticipant.getOrganisme());
        participant.setProfil(detailsParticipant.getProfil());
        participant.setPays(detailsParticipant.getPays());
        
        MessageResponse updatedParticipant = participantService.save(participant);
        return updatedParticipant;
    }

   @GetMapping("{id}")
    public Participant findById(@PathVariable("id") Long id) {
        return participantService.findById(id);
    }

    @DeleteMapping("deleteParticipant/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return participantService.delete(id);
    }
}