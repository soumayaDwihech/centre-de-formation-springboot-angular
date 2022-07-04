package world.cup.controller;

import world.cup.models.Pays;
import world.cup.payload.response.*;
import world.cup.service.PaysServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@CrossOrigin("*")
@RequestMapping("/pays")
public class PaysController {

    @Autowired
    private PaysServiceImpl paysService;

    //@RequestMapping(value="/pays", method=RequestMethod.GET)
    @GetMapping("/allPays")
    public List<Pays> findAll() {
        return paysService.findAll();
    }

    @PostMapping("/addPays")
    public MessageResponse save(@RequestBody Pays pays) {
        return paysService.save(pays);
    }

    @PutMapping("/updatePays/{id}")
    public MessageResponse updatePays(@PathVariable(value = "id") Long Id, @Validated @RequestBody Pays detailsPays) {

        Pays pays = paysService.findById(Id);
        pays.setNom(detailsPays.getNom());
        
        MessageResponse updatedPays = paysService.save(pays);
        return updatedPays;
    }
    
   @GetMapping("/{id}")
    public Pays findById(@PathVariable("id") Long id) {
        return paysService.findById(id);
    }

    @DeleteMapping("deletePays/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return paysService.delete(id);
    }
}

