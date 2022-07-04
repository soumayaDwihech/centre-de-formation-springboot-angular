package world.cup.service;

import world.cup.models.Session_formation;
import world.cup.repositories.Session_FormationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ImplSessionFormationService  implements SessionFormationService {

@Autowired
Session_FormationRepository session_FormationRepository;

@Override
public Session_formation save(Session_formation session) {
	return session_FormationRepository.save(session);
}
@Transactional
@Override
public Session_formation update(Session_formation session) {
	boolean existe = session_FormationRepository.existsById(session.getId());
  
   return session_FormationRepository.save(session);
    
}
@Transactional
@Override
public void delete(Long id) {

	Session_formation session = findById(id);
  
	session_FormationRepository.delete(session);
   
}    

@Override
public List<Session_formation> findAll() {
	return session_FormationRepository.findAll();
}

@Override
public Session_formation findById(Long id) {
	Session_formation session= session_FormationRepository.findById(id).orElse(null);
    return session;
}

}