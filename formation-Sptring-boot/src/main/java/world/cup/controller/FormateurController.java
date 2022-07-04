package world.cup.controller;

import world.cup.models.Formateur;
import world.cup.payload.response.*;
import world.cup.service.ImlpFormateurService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController
@CrossOrigin("*")
@RequestMapping("/Formateur")
public class FormateurController {

    @Autowired
    ImlpFormateurService formateurService;
    
    @GetMapping("/allFormateurs")
    public List<Formateur> findAll() {
        return formateurService.findAll();
    }

    @PostMapping("/addFormateur")
    public Formateur createFormateur(@Validated @RequestBody Formateur formateur) {
        return formateurService.save(formateur);
    }


    @DeleteMapping("deleteFormateur/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return formateurService.delete(id);
    }

    @PutMapping("/updateFormateur/{id}")
    public Formateur updateFormateur(@PathVariable(value = "id") Long Id, @Validated @RequestBody Formateur detailsFormateur) {

        Formateur formateur = formateurService.findById(Id);


        formateur.setMail(detailsFormateur.getMail());
        formateur.setNom(detailsFormateur.getNom());
        formateur.setPernom(detailsFormateur.getPernom());
        formateur.setType(detailsFormateur.getType());

        Formateur updateFormateur1 = formateurService.save(formateur);
        return updateFormateur1;
    }

}