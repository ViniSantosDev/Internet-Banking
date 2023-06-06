package br.com.ViniSantosDev.Internet.Banking.DTO;

import br.com.ViniSantosDev.Internet.Banking.entities.Cliente;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="historico")
public class TransacoesDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDate data;

    public LocalDate getData() {
        return data;
    }
}
