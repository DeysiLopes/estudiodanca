package lpoo.estudiodanca.modelo.rn;

import java.util.List;

import lpoo.estudiodanca.modelo.dao.GenericDao;
import lpoo.estudiodanca.modelo.vo.Contato;
import lpoo.estudiodanca.modelo.vo.Estudante;

public class EstudanteRN {

    private GenericDao<Estudante> genericDao;

    public EstudanteRN(){
        genericDao = new GenericDao<Estudante>();
    }

    public void gravar(Estudante estudante){
        genericDao.save(estudante);
    }
    
    public void gravar(Estudante estudante, List<Contato> contatos){
        genericDao.getManager().getTransaction().begin();

        contatos.forEach(contato -> {
            genericDao.getManager().persist(contato);
            estudante.addContato(contato);
        });

        genericDao.getManager().persist(estudante);
        genericDao.getManager().flush();
        genericDao.getManager().getTransaction().commit();
        System.out.println(estudante.getClass().getSimpleName() + "Salvo com sucesso!");
    }

    public void atualizar(Estudante estudante){
        genericDao.update(estudante);
    }

    public void atualizar(Estudante estudante, List<Contato> contatos){
        genericDao.getManager().getTransaction().begin();

        contatos.forEach(contato -> {
            genericDao.getManager().persist(contato);
            estudante.addContato(contato);
        });
    }

        public void remover(Estudante estudante){
            genericDao.delete(estudante);
        }

        public void remover(Estudante estudante, List<Contato> contatos){
            genericDao.getManager().getTransaction().begin();

            contatos.forEach(contato -> {
                genericDao.getManager().remove(contato);
            });

            genericDao.getManager().remove(estudante);
            genericDao.getManager().getTransaction().commit();
            System.out.println(estudante.getClass().getSimpleName() + " excluído com sucesso!");
        }

        public List buscarTodos(){
            List<Estudante> estudantes = genericDao.listAll(Estudante.class);
            return estudantes;
        }
        public List buscar(String nome, String nomePessoa){
            try {
                return genericDao.search(nome, nomePessoa, Estudante.class);
            } catch (Exception e) {
                return null;
            }
        }
        public Estudante buscar(Long id){
            try {
                return genericDao.search(Estudante.class, id);
            } catch (Exception e) {
                return null;
            }
        }
}
