package lpoo.estudiodanca.modelo.services;

import java.util.ArrayList;
import java.util.List;

import lpoo.estudiodanca.modelo.dao.DaoFactory;
import lpoo.estudiodanca.modelo.dao.TurmaDao;
import lpoo.estudiodanca.modelo.vo.Turma;

public class TurmaService {
	
	private TurmaDao dao = new DaoFactory().createTurmaDao();
	
	public List<Turma> findAll(){
//		List<Turma> list = new ArrayList<>();
//		list.add(new Turma(1, "Jazz", null));
//		list.add(new Turma(2, "Jazz", null));
//		list.add(new Turma(3, "Jazz", null));
//		return list;
		return dao.findAll();
		
	}
}
