package world.cup.controller;

import world.cup.models.Domaine;
import world.cup.payload.response.*;
import world.cup.service.DomaineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@CrossOrigin("*")
@RequestMapping("/domaine")
public class DomaineController {

    @Autowired
    private DomaineServiceImpl domaineService;

    //@RequestMapping(value="/domaine", method=RequestMethod.GET)
    @GetMapping("/domaines")
    public List<Domaine> findAll() {
        return domaineService.findAll();
    }

    @PostMapping("/saveDomaine")
    public MessageResponse save(@RequestBody Domaine domaine) {
        return domaineService.save(domaine);
    }

    @PutMapping("/updateDomaine/{id}")
    public MessageResponse updateDomaine(@PathVariable(value = "id") Long Id, @Validated @RequestBody Domaine detailsDomaine) {

        Domaine domaine = domaineService.findById(Id);
        domaine.setLibelle(detailsDomaine.getLibelle());
        
        MessageResponse updatedDomaine = domaineService.save(domaine);
        return updatedDomaine;
    }
    

   @GetMapping("/{code}")
    public Domaine findById(@PathVariable("code") Long id) {
        return domaineService.findById(id);
    }

    @DeleteMapping("deleteDomaine/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return domaineService.delete(id);
    }
}


