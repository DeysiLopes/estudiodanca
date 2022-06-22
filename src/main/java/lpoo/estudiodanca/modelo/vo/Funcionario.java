package lpoo.estudiodanca.modelo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_funcionario")
//@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long cod;
    private String nome;
    private String cpf;
    private String rg;

    //@Temporal(TemporalType.DATE)
    private String dataNascimento;

    @OneToMany(mappedBy = "funcionario", targetEntity = Contato.class, cascade = CascadeType.ALL)
    private List<Contato> contatos;    
    private String sexo;


    @Embedded
    @OneToOne(targetEntity = Endereco.class, cascade = CascadeType.ALL)
    private Endereco endereco;

    public Funcionario() {
        this.contatos = new ArrayList<Contato>();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Contato> getContato() {
        return contatos;
    }

    public void addContato(Contato contato) {
        if (!getContato().contains(contato)) {
            this.contatos.add(contato);
            if (contato.getFuncionario() != null) {
                contato.getFuncionario().getContato().remove(contato);
            }
            contato.setFuncionario(this);
        }
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
