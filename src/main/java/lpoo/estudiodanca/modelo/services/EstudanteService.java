package lpoo.estudiodanca.modelo.services;

import java.util.ArrayList;
import java.util.List;

import lpoo.estudiodanca.modelo.dao.DaoFactory;
import lpoo.estudiodanca.modelo.dao.EstudanteDao;
import lpoo.estudiodanca.modelo.vo.Estudante;
import lpoo.estudiodanca.modelo.vo.Turma;

public class EstudanteService<T> {
	
	private EstudanteDao dao = new DaoFactory().createEstudanteDao();
	
	public List<Estudante> findAll(){
		return dao.findAll();
}
}
