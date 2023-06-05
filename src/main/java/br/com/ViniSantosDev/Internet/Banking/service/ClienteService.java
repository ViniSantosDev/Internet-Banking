package br.com.ViniSantosDev.Internet.Banking.service;

import br.com.ViniSantosDev.Internet.Banking.DTO.ClienteDTO;
import br.com.ViniSantosDev.Internet.Banking.entities.Cliente;
import br.com.ViniSantosDev.Internet.Banking.repository.ClienteRepository;
import br.com.ViniSantosDev.Internet.Banking.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

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
        cliente = repository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO put(Long id, ClienteDTO body) throws Exception {
        try {
            Cliente cliente = repository.getReferenceById(id);
            convertToClienteToDTO(cliente);
            cliente = repository.save(cliente);
            return new ClienteDTO(cliente);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            throw new NotFoundException("ID not found" + e);

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
        clienteDTO.setDeposito(cliente.getDeposito());
        return clienteDTO;
    }


}



