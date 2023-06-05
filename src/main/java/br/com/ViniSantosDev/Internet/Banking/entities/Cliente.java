package br.com.ViniSantosDev.Internet.Banking.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;
    @Column
    private Boolean planoExclusive;
    @Column
    private BigDecimal saldo;
    @Column
    private String numeroConta;
    @Column
    private Date dataNascimento;
    @Column
    private BigDecimal deposito;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getPlanoExclusive() {
        return planoExclusive;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public BigDecimal getDeposito() {
        return deposito;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPlanoExclusive(Boolean planoExclusive) {
        this.planoExclusive = planoExclusive;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDeposito(BigDecimal deposito) {
        this.deposito = deposito;
    }
}
