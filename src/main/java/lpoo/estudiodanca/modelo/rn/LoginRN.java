package com.mycompany.lpoofinal.modelo.rn;

import java.util.List;

import lpoo.estudiodanca.modelo.dao.GenericDao;
import lpoo.estudiodanca.modelo.vo.Login;

public class LoginRN {
    
    private GenericDao<Login> genericDao;

    public LoginRN() {
        this.genericDao = new GenericDao<Login>();
    }

    public void gravar(Login login) {
        genericDao.save(login);
    }
    
    public void atualizar(Login login) {
        genericDao.update(login);
    }

    public void remover(Login login) {
        genericDao.delete(login);
    }

    public List buscarTodos() {
        return genericDao.listAll(Login.class);
    }

    public Login buscar(String login) {
        try {
            return genericDao.listOne("usuario", login, Login.class);
        } catch (Exception e) {
            return null;
        }
    }
}