package lpoo.estudiodanca.modelo.dao;

import lpoo.estudiodanca.modelo.db.DB;
import lpoo.estudiodanca.modelo.impl.EstudanteDaoImpl;
import lpoo.estudiodanca.modelo.impl.FuncionarioDaoImpl;
import lpoo.estudiodanca.modelo.impl.TurmaDaoImpl;

public class DaoFactory {

	public static EstudanteDao createEstudanteDao() {
		return new EstudanteDaoImpl(DB.getConnection());
		
	}
	public static FuncionarioDao createFuncionarioDao() {
		return new FuncionarioDaoImpl(DB.getConnection());
		
	}
	public static TurmaDao createTurmaDao() {
		return new TurmaDaoImpl(DB.getConnection());
		
	}
}
