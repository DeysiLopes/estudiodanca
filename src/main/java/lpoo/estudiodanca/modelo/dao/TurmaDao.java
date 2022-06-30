package lpoo.estudiodanca.modelo.dao;

import java.util.List;

import lpoo.estudiodanca.modelo.vo.Turma;

public interface TurmaDao {
	void insert(Turma obj);
	void update(Turma obj);
	void deleteById(Integer id);
	//Turma findById(Integer id);
	List<Turma> findAll();
}
