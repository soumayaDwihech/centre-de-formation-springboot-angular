package world.cup.service;

import world.cup.models.Formation;

public interface IFormationService {

    Formation save(Formation formation);
    void delete(Formation formation);
    Formation findById(Long id);
}