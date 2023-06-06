package br.com.ViniSantosDev.Internet.Banking.controller;

import br.com.ViniSantosDev.Internet.Banking.DTO.ClienteDTO;
import br.com.ViniSantosDev.Internet.Banking.DTO.TransacoesDTO;
import br.com.ViniSantosDev.Internet.Banking.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> getAllClientes(){
        try {
            Page<ClienteDTO> page = service.findAllCliente();
            return ResponseEntity.status(HttpStatus.OK).body(page);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        try {
            ClienteDTO dto = service.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }
    @PostMapping
     public ResponseEntity<ClienteDTO> insert(@RequestBody ClienteDTO body) {
        try{
            ClienteDTO dto = service.insert(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
     }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO body){
        try{
            ClienteDTO dto = service.update(id, body);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PostMapping(value = "/{id}/depositar")
    public ResponseEntity depositar(@PathVariable("id") Long id, @RequestBody BigDecimal valor){
      service.depositar(id, valor);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping(value = "/{id}/sacar")
    public ResponseEntity sacar(@PathVariable("id") Long id, @RequestBody BigDecimal valor) throws Exception {
        service.sacar(id, valor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/historico/{data}")
    public ResponseEntity<List<TransacoesDTO>> historico(
            @PathVariable Long id,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
        ClienteDTO cliente = service.findById(id);
        List<TransacoesDTO> transacoes = service.historico(cliente, data);
        return ResponseEntity.ok(transacoes);
    }

}
