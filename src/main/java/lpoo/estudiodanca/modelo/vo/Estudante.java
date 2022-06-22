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
import javax.persistence.Table;

@Entity
@Table(name = "tb_estudante")
@Inheritance(strategy = InheritanceType.JOINED)

public class Estudante implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long cod;
    private String nome;
    
    public Estudante(Long cod, String nome) {
		this.cod = cod;
		this.nome = nome;
	}

    

	public Long getCod() {
		return cod;
	}



	public void setCod(Long cod) {
		this.cod = cod;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addContato(Contato contato) {
		// TODO Auto-generated method stub
		
	}
}
