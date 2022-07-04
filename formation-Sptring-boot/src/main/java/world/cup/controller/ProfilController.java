package world.cup.controller;

import world.cup.models.Profil;
import world.cup.payload.response.*;
import world.cup.service.ProfilServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@CrossOrigin("*")
@RequestMapping("/Profil")
public class ProfilController {

    @Autowired
    private ProfilServiceImpl profilService;

    //@RequestMapping(value="/profil", method=RequestMethod.GET)
    @GetMapping("/profiles")
    public List<Profil> findAll() {
        return profilService.findAll();
    }

    @PostMapping("/saveProfil")
    public MessageResponse save(@RequestBody Profil profil) {
        return profilService.save(profil);
    }

    @PutMapping("/updateProfil/{id}")
    public MessageResponse updateProfil(@PathVariable(value = "id") Long Id, @Validated @RequestBody Profil detailsProfil) {

        Profil profil = profilService.findById(Id);
        profil.setLibelle(detailsProfil.getLibelle());
        
        MessageResponse updatedProfil = profilService.save(profil);
        return updatedProfil;
    }

   @GetMapping("/id")
    public Profil findById(@PathVariable("id") Long id) {
        return profilService.findById(id);
    }

    @DeleteMapping("deleteProfil/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return profilService.delete(id);
    }
}
