package lpoo.estudiodanca.modelo.dao;

import java.util.List;

import lpoo.estudiodanca.modelo.vo.Estudante;
import lpoo.estudiodanca.modelo.vo.Funcionario;
import lpoo.estudiodanca.modelo.vo.Turma;

public interface EstudanteDao {
	void insert(Estudante obj);
	void update(Estudante obj);
	void deleteById(Integer id);
	Estudante findById(Integer id);
	List<Estudante> findAll();
	List<Estudante> findByTurma(Turma turma);
}
