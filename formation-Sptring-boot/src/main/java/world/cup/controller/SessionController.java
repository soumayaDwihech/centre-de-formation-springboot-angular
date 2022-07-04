package world.cup.controller;

import world.cup.models.Participant;
import world.cup.models.Session_formation;
import world.cup.service.ImplSessionFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import world.cup.service.ParticipantService;
import world.cup.service.ParticipantServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Controller
//@ResponseBody
@RestController
@CrossOrigin("*")
@RequestMapping("/Session")
public class SessionController {

    @Autowired
    private ImplSessionFormationService sessionService;
    @Autowired
    private ParticipantServiceImpl participantService;
    //@RequestMapping(value="/session", method=RequestMethod.GET)
    @GetMapping("/allSessions")
    public List<Session_formation> findAll() {
        return sessionService.findAll();
    }

    @PostMapping("/AddSession")
    public Session_formation save(@RequestBody Session_formation session) {
        return sessionService.save(session);
    }

    @PutMapping("/updateSession/{id}")
    public Session_formation updateSession_formation(@PathVariable(value = "id") Long Id, @Validated @RequestBody Session_formation detailsSession_formation) {

        Session_formation session_formation = sessionService.findById(Id);
        session_formation.setLieu(detailsSession_formation.getLieu());
        session_formation.setDate_debut(detailsSession_formation.getDate_debut());
        session_formation.setDate_fin(detailsSession_formation.getDate_fin());
        session_formation.setNb_participant(detailsSession_formation.getNb_participant());
        session_formation.setFormateur(detailsSession_formation.getFormateur());
        session_formation.setFormation(detailsSession_formation.getFormation());
        session_formation.setOrganisme(detailsSession_formation.getOrganisme());
        Set<Participant> lp = new HashSet<>();
        if (detailsSession_formation.getParticipant() != null) {
            for (Participant p : detailsSession_formation.getParticipant()) {
                Participant participant = participantService.findById(p.getId());
                lp.add(participant);

            }
            System.out.println("----------------------------------");
            System.out.println(lp);
        session_formation.setParticipant(lp);
        }
        Session_formation updatedSession_formation = sessionService.save(session_formation);
        return updatedSession_formation;
    }
    

   @GetMapping("{id}")
    public Session_formation findById(@PathVariable("id") Long id) {
        return sessionService.findById(id);
    }

    @DeleteMapping("deleteSession/{id}")
    public void delete(@PathVariable Long id) {
        sessionService.delete(id);
    }
}

