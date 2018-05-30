package br.com.itsimple.smartcities.dao.impl;

import javax.persistence.EntityManager;

import br.com.itsimple.smartcities.dao.ClienteDAO;
import br.com.itsimple.smartcities.entity.Cliente;

public class ClienteDAOImpl extends GenericDAOImpl<Cliente, Long> implements ClienteDAO{

    public ClienteDAOImpl(EntityManager em) {
        super(em);
    }

}
