package lpoo.estudiodanca.modelo.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tb_turma")
public class Turma {
	@Id
	int id;
	String nome;
	Date horario;

	public Turma(int id, String nome, Date horario) {
		this.id = id;
		this.nome = nome;
		this.horario = horario;
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