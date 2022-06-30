package lpoo.estudiodanca.modelo.dao;

import java.util.List;

import lpoo.estudiodanca.modelo.vo.Funcionario;
import lpoo.estudiodanca.modelo.vo.Turma;

public interface FuncionarioDao {
	void insert(Funcionario obj);
	void update(Funcionario obj);
	void deleteById(Integer id);
	Funcionario findById(Integer id);
	List<Funcionario> findAll();
	List<Funcionario> findByTurma(Turma turma);
}
