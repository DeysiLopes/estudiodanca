package lpoo.estudiodanca.modelo.vo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_estudante")
@Inheritance(strategy = InheritanceType.JOINED)

public class Estudante implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	@Column
    private String nome;
	@Column
    private Turma TurmaId;
	
	

	public Estudante(int id, String nome, Turma TurmaId) {
		this.id = id;
		this.nome = nome;
		this.TurmaId = TurmaId;
	}

	public Turma getTurmaId() {
		return TurmaId;
	}

	public void setTurmaId(Turma turma) {
		this.TurmaId = TurmaId;
	}

	public Estudante() {
		
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
	
}
