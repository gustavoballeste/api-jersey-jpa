package br.com.itsimple.smartcities.entity.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.itsimple.smartcities.entity.Cliente;

@XmlRootElement
public class Clientes {

    private List<Cliente> clientes = new ArrayList<>();

    public Clientes() {
    }

    public Clientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @XmlElement(name = "cliente")
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}