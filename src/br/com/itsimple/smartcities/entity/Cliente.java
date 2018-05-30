package br.com.itsimple.smartcities.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TBL_CLIENTE", schema = "AKE")
@SequenceGenerator(name = "sqCliente", sequenceName = "SEQ_CLIENTE", schema = "AKE", allocationSize = 1)
@XmlRootElement(name = "Cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqCliente")
    @Column(name = "cd_cliente")
    private Long idCliente;
    @Column(name = "nm_cliente", nullable = false, length = 40)
    private String nome;
    @Column(name = "ds_sobrenome", nullable = false, length = 60)
    private String sobrenome;
    @Column(name = "ds_email", nullable = false, unique = true, length = 80)
    private String email;

    public Cliente(Long idCliente, String nome, String sobrenome, String email) {
        super();
        this.idCliente = idCliente;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
    }

    public Cliente() {
    }

    @XmlElement(name = "idCliente", required = true)
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    @XmlElement(name = "nome", required = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlElement(name = "sobrenome", required = true)
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @XmlElement(name = "email", required = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email
                + "]";
    }

}