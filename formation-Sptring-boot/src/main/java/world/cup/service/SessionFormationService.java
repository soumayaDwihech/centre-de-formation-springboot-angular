package world.cup.service;

import java.util.List;

import world.cup.models.Session_formation;

public interface SessionFormationService {
	
	public Session_formation save(Session_formation session); 
    public Session_formation update(Session_formation session);
    public void delete(Long id);
    public List<Session_formation> findAll();
    public  Session_formation findById(Long id);
    
}