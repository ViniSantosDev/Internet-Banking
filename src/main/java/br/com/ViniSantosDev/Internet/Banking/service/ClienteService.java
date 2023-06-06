package br.com.ViniSantosDev.Internet.Banking.service;

import br.com.ViniSantosDev.Internet.Banking.DTO.ClienteDTO;
import br.com.ViniSantosDev.Internet.Banking.DTO.TransacoesDTO;
import br.com.ViniSantosDev.Internet.Banking.entities.Cliente;
import br.com.ViniSantosDev.Internet.Banking.repository.ClienteRepository;
import br.com.ViniSantosDev.Internet.Banking.service.exceptions.DataBaseException;
import br.com.ViniSantosDev.Internet.Banking.service.exceptions.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;


    public Page<ClienteDTO> findAllCliente() {
        try {
            Pageable pageable = PageRequest.of(0, 100);
            Page<Cliente> clientes = repository.findAll(pageable);
            return pageConvertDTO(clientes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Transactional
    public ClienteDTO findById(Long id) {
        Optional<Cliente> obj = repository.findById(id);
        Cliente cliente = obj.get();
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setPlanoExclusive(dto.getPlanoExclusive());
        cliente.setNumeroConta(dto.getNumeroConta());
        cliente.setSaldo(dto.getSaldo());
        cliente = repository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) throws Exception {
        try {
            Cliente cliente = repository.getReferenceById(id);
            cliente.setNome(dto.getNome());
            cliente.setDataNascimento(dto.getDataNascimento());
            cliente.setPlanoExclusive(dto.getPlanoExclusive());
            cliente.setNumeroConta(dto.getNumeroConta());
            cliente.setSaldo(dto.getSaldo());
            cliente = repository.save(cliente);
            return new ClienteDTO(cliente);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            throw new ResourceException("ID not found" + e);

        }
    }

    @Transactional
    public void depositar(Long id, BigDecimal valor) {
        try {
            Cliente cliente = repository.findById(id).get();
            BigDecimal saldo = cliente.getSaldo();
            if (saldo == null) {
                saldo = BigDecimal.ZERO;
            }
            cliente.setSaldo(cliente.getSaldo().add(valor));
            repository.save(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Deposito feito");
    }

    @Transactional
    public void sacar(Long id, BigDecimal valor) throws ResourceException {
        Optional<Cliente> optionalCliente = repository.findById(id);
        BigDecimal saldo;
        BigDecimal taxaAdm;
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            saldo = cliente.getSaldo();
            repository.save(cliente);
            if (saldo == null) {
                saldo = BigDecimal.ZERO;
            }
            if (cliente.getPlanoExclusive()) {
                taxaAdm = BigDecimal.ZERO;
            } else {
                if (valor.compareTo(BigDecimal.valueOf(100)) <= 0) {
                    taxaAdm = BigDecimal.ZERO;
                } else if (valor.compareTo(BigDecimal.valueOf(300)) <= 0) {
                    taxaAdm = valor.multiply(BigDecimal.valueOf(0.004));
                } else {
                    taxaAdm = valor.multiply(BigDecimal.valueOf(0.01));
                }
            }
            if (valor.add(taxaAdm).compareTo(saldo) <= 0) {
                cliente.setSaldo(saldo.subtract(valor.add(taxaAdm)));
                repository.save(cliente);
                System.out.println("Saque realizado");
            } else {
                throw new ResourceException("Saldo insuficiente");
            }
        } else {
            throw new ResourceException("Cliente não encontrado");
        }

    }


    public Page<ClienteDTO> pageConvertDTO(Page<Cliente> clientes) {
        return clientes.map(cliente -> this.convertToClienteToDTO(cliente));
    }

    public ClienteDTO convertToClienteToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setPlanoExclusive(cliente.getPlanoExclusive());
        clienteDTO.setSaldo(cliente.getSaldo());
        clienteDTO.setDataNascimento(cliente.getDataNascimento());
        return clienteDTO;
    }



    public List<TransacoesDTO> historico (ClienteDTO cliente, LocalDate data) {
        return cliente.getHistorico().stream().filter(dto -> dto.getData().equals(data)).collect(Collectors.toList());
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceException("ID not found" + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }
    }
}



