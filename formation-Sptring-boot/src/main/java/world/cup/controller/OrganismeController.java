package world.cup.controller;

import world.cup.models.Organisme;
import world.cup.payload.response.*;
import world.cup.service.OrganismeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@CrossOrigin("*")
@RequestMapping("/organisme")
public class OrganismeController {

    @Autowired
    private OrganismeServiceImpl organismeService;

    //@RequestMapping(value="/user", method=RequestMethod.GET)
    @GetMapping("/allOrganisme")
    public List<Organisme> findAll() {
        return organismeService.findAll();
    }

    @PostMapping("/addOrganisme")
    public MessageResponse save(@RequestBody Organisme organisme) {
        return organismeService.save(organisme);
    }

    @PutMapping("/updateOrganisme/{id}")
    public MessageResponse updateOrganisme(@PathVariable(value = "id") Long Id, @Validated @RequestBody Organisme detailsOrganisme) {

        Organisme organisme = organismeService.findById(Id);
        organisme.setLibelle(detailsOrganisme.getLibelle());
        
        MessageResponse updatedOrganisme = organismeService.save(organisme);
        return updatedOrganisme;
    }

   @GetMapping("/{id}")
    public Organisme findById(@PathVariable("id") Long id) {
        return organismeService.findById(id);
    }

    @DeleteMapping("deleteOrganisme/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return organismeService.delete(id);
    }
}
