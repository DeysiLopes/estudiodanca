package lpoo.estudiodanca.modelo.rn;

import java.util.List;

import lpoo.estudiodanca.modelo.dao.GenericDao;
import lpoo.estudiodanca.modelo.vo.Contato;
import lpoo.estudiodanca.modelo.vo.Funcionario;

public class FuncionarioRN {

    private GenericDao<Funcionario> genericDao;

    public FuncionarioRN(){
        genericDao = new GenericDao<Funcionario>();
    }
    
    public void gravar(Funcionario fun){
        genericDao.save(fun);
    }

    public void gravar(Funcionario fun, List<Contato> contatos){
        genericDao.getManager().getTransaction().begin();

        contatos.forEach(contato -> {
            genericDao.getManager().persist(contato);
            fun.addContato(contato);
        });
        genericDao.getManager().persist(fun);
        genericDao.getManager().flush();
        genericDao.getManager().getTransaction().commit();
        System.out.println(fun.getClass().getSimpleName() + " salvo com sucesso!");
    }
    public void atualizar(Funcionario fun) {
        genericDao.update(fun);
    }

    public void atualizar(Funcionario fun, List<Contato> contatos) {
        genericDao.getManager().getTransaction().begin();

        contatos.forEach(contato -> {
            genericDao.getManager().persist(contato);
            fun.addContato(contato);
        });

        genericDao.getManager().persist(fun);
        genericDao.getManager().getTransaction().commit();
        System.out.println(fun.getClass().getSimpleName() + " atualizado com sucesso!");
    }

    public void remover(Funcionario fun) {
        genericDao.delete(fun);
    }

    public void remover(Funcionario fun, List<Contato> contatos) {
        genericDao.getManager().getTransaction().begin();

        contatos.forEach(contato -> {
            genericDao.getManager().remove(contato);
        });

        genericDao.getManager().remove(fun);
        genericDao.getManager().getTransaction().commit();
//        System.out.println(contato.getClass().getSimpleName() + " excluído com sucesso!");
        System.out.println(fun.getClass().getSimpleName() + " excluído com sucesso!");
    }

    public List buscarTodos() {
        List<Funcionario> funcionarios = genericDao.listAll(Funcionario.class);

        return funcionarios;
    }

    public List buscar(String nome, String nomePessoa) {
        try {
            return genericDao.search(nome, nomePessoa, Funcionario.class);
        } catch (Exception e) {
            return null;
        }
    }

    public Funcionario buscar(Long id) {
        try {
            return genericDao.search(Funcionario.class, id);
        } catch (Exception e) {
            return null;
        }
    }
}