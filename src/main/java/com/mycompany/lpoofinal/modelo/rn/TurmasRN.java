package com.mycompany.lpoofinal.modelo.rn;

import java.util.List;

import lpoo.estudiodanca.modelo.dao.GenericDao;
import lpoo.estudiodanca.modelo.vo.Contato;
import lpoo.estudiodanca.modelo.vo.Funcionario;
import lpoo.estudiodanca.modelo.vo.Turma;

public class TurmasRN {
    private GenericDao<Turma> genericDao;

    public void TurmaRN() {
        genericDao = new GenericDao<Turma>();
    }

    public void gravar(Turma pro) {
        genericDao.save(pro);
    }

    public void atualizar(Turma pro) {
        genericDao.update(pro);
    }

    public void remover(Turma pro) {
        genericDao.delete(pro);
    }

    //ARRUMAR
    public void remover(Turma pro, List<Contato> contatos) {
        genericDao.getManager().getTransaction().begin();

        contatos.forEach(contato -> {
            genericDao.getManager().remove(contato);
        });

        genericDao.getManager().remove(pro);
        genericDao.getManager().getTransaction().commit();
//        System.out.println(contato.getClass().getSimpleName() + " excluído com sucesso!");
        System.out.println(pro.getClass().getSimpleName() + " excluído com sucesso!");
    }

    public List buscarTodos() {
        List<Turma> Turmas = genericDao.listAll(Turma.class);

        return Turmas;
    }

    public List buscar(String nome, String nomePessoa) {
        try {
            return genericDao.search(nome, nomePessoa, Turma.class);
        } catch (Exception e) {
            return null;
        }
    }

    public Funcionario buscar(Long id) {
        try {
            return genericDao.search(Turma.class, id);
        } catch (Exception e) {
            return null;
        }
    }
}