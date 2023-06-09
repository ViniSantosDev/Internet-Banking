package br.com.ViniSantosDev.Internet.Banking.DTO;

import br.com.ViniSantosDev.Internet.Banking.entities.Cliente;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ClienteDTO {

    private Long id;

    private String nome;
    private Boolean planoExclusive;
    private BigDecimal saldo;
    private String numeroConta;
    private Date dataNascimento;
    private BigDecimal deposito;

    private List<TransacoesDTO> historico;

    public ClienteDTO() {

    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.planoExclusive = cliente.getPlanoExclusive();
        this.saldo = cliente.getSaldo();
        this.numeroConta = cliente.getNumeroConta();
        this.dataNascimento = cliente.getDataNascimento();
        this.historico = cliente.getHistorico();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TransacoesDTO> getHistorico() {
        return historico;
    }
}
