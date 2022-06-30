package lpoo.estudiodanca.modelo.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tb_turma")
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	@Column
	String nome;
	
	@Column
	Date horario;

	@Column
	Funcionario fun;

	
	public Turma(int id, String nome, Date horario, Funcionario fun) {
		this.id = id;
		this.nome = nome;
		this.horario = horario;
		this.fun = fun;
	}

	public Funcionario getFun() {
		return fun;
	}

	public void setFun(Funcionario fun) {
		this.fun = fun;
	}

	public Turma() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//@Temporal(value = null)
	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}


}