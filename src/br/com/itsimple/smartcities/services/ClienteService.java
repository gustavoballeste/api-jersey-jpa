package br.com.itsimple.smartcities.services;

import java.net.URI;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.com.itsimple.smartcities.dao.ClienteDAO;
import br.com.itsimple.smartcities.dao.impl.ClienteDAOImpl;
import br.com.itsimple.smartcities.entity.Cliente;
import br.com.itsimple.smartcities.singleton.EntityManagerFactorySingleton;

@PermitAll
@Path("/usuario")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ClienteService {

    private EntityManager em;
    private ClienteDAO dao;

    public ClienteService() {
        em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        dao = new ClienteDAOImpl(em);
    }

    @GET
    @Path("ola")
    @Produces("text/plain;charset=utf8")
    public String testaServico() {
        return "Serviço REST em funcionamento!";
    }
    
    @GET
    @RolesAllowed("guest")
    public List<Cliente> buscarTodos() {
        return dao.listar();
    }

    @GET
    @Path("{id}")
    @RolesAllowed("guest")
    public Cliente buscarPorId(@PathParam("id") Long id) {
        return dao.buscar(id);
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("user")
    public void removerPorId(@PathParam("id") Long id) {
        try {
            dao.remover(id);
            dao.commit();
        } catch (Exception e) {
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @RolesAllowed("user")
    public Response criarUsuario(Cliente cliente) {
        try {
            dao.cadastrar(cliente);
            dao.commit();
            URI uri = UriBuilder.fromPath("usuario/{id}").build(cliente.getIdCliente());
            return Response.created(uri).entity(cliente).build();
        } catch (Exception e) {
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    @RolesAllowed("user")
    public Cliente atualizar(@PathParam("id") Long id, Cliente cliente) {
        try {
            dao.atualizar(cliente);
            dao.commit();
            return cliente;
        } catch (Exception e) {
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }
    }

}
