package lpoo.estudiodanca.modelo.rn;

import java.util.List;

import lpoo.estudiodanca.modelo.dao.GenericDao;
import lpoo.estudiodanca.modelo.vo.Funcionario;
import lpoo.estudiodanca.modelo.vo.Tipo;

public class TipoRN {

    private GenericDao<Tipo> genericDao;

    public TipoRN() {
        genericDao = new GenericDao<Tipo>();
    }

    public void gravar(Tipo tipo) {
        genericDao.save(tipo);
    }

    public void atualizar(Tipo tipo) {
        genericDao.update(tipo);
    }

    public void remover(Tipo tipo) {
        genericDao.delete(tipo);
    }

    public List buscarTodos() {
        List<Tipo> tipo = genericDao.listAll(Tipo.class);

        return tipo;
    }

    public List buscar(String key, String value) {
        try {
            return genericDao.search(key, value, Tipo.class);
        } catch (Exception e) {
            return null;
        }
    }

    public Tipo buscar(String value) {
        try {
            return genericDao.listOne("tipoFuncionario", value, Tipo.class);
        } catch (Exception e) {
            return null;
        }
    }

    public Funcionario buscar(Long id) {
        try {
            return genericDao.search(Tipo.class, id);
        } catch (Exception e) {
            return null;
        }
    }
    
}