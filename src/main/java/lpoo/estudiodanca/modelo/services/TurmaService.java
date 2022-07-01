package lpoo.estudiodanca.modelo.services;

import java.util.ArrayList;
import java.util.List;

import lpoo.estudiodanca.modelo.dao.DaoFactory;
import lpoo.estudiodanca.modelo.dao.TurmaDao;
import lpoo.estudiodanca.modelo.vo.Turma;

public class TurmaService {
	
	private TurmaDao dao = new DaoFactory().createTurmaDao();
	
	public List<Turma> findAll(){
		return dao.findAll();
		
	}
	
	public void saveOrUpdate(Turma obj) {
		if(obj.getId() == 0) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}
}
