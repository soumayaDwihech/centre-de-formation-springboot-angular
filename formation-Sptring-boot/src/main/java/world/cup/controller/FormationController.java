package world.cup.controller;

import world.cup.models.Formation;
import world.cup.models.Participant;
import world.cup.payload.response.*;
import world.cup.service.ImplFormationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController
@CrossOrigin("*")
@RequestMapping("/Formation")
public class FormationController {

    @Autowired
    ImplFormationService formationService;

    
    @GetMapping("/allFormations")
    public List<Formation> findAll() {
        return formationService.findAll();
    }

    @PostMapping("/Formation")
    public Formation createFormation(@Validated @RequestBody Formation formation) {
        return formationService.save(formation);
    }


    @GetMapping("{id}")
    public Formation findById(@PathVariable("id") Long id) {
        return formationService.findById(id);
    }

    @DeleteMapping("deleteFormation/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return formationService.delete(id);
    }

    @PutMapping("/Updateformation/{id}")
    public Formation updateFormation(@PathVariable(value = "id") Long Id, @Validated @RequestBody Formation detailsFormation) {

        Formation formation = formationService.findById(Id);
        formation.setType_formation(detailsFormation.getType_formation());
        formation.setBudget(detailsFormation.getBudget());
        formation.setDuree(detailsFormation.getDuree());
        formation.setTitre(detailsFormation.getTitre());
        formation.setAnnee(detailsFormation.getAnnee());
        formation.setDomaine(detailsFormation.getDomaine());
        formation.setNb_session(detailsFormation.getNb_session());
        if (detailsFormation.getSession_formation() != null){
                formation.setSession_formation(detailsFormation.getSession_formation());
        }

        Formation updatedFormation = formationService.save(formation);

        return updatedFormation;
    }

}
