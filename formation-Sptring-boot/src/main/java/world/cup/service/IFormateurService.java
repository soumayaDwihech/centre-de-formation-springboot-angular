package world.cup.service;

import world.cup.models.Formateur;

public interface IFormateurService {

        Formateur save(Formateur formateur);
        void delete(Formateur formateur);
        Formateur findById(Long id);
}