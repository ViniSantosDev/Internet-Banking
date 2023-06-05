package br.com.ViniSantosDev.Internet.Banking.controller;

import br.com.ViniSantosDev.Internet.Banking.DTO.ClienteDTO;
import br.com.ViniSantosDev.Internet.Banking.service.ClienteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Api(value = "clientes")
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
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody @Valid ClienteDTO body){
        try{
            ClienteDTO dto = service.put(id, body);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping(value = "/{id}/depositar")
    public ResponseEntity<Page<ClienteDTO>> depositar(@PathVariable("id") @RequestBody ClienteDTO dto) throws Exception{
      return null;
    }
    @PostMapping(value = "/{id}/sacar")
    public ResponseEntity<Page<ClienteDTO>> sacar() throws Exception{
        return null;
    }

}
