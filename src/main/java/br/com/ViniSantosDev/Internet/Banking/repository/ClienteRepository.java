package br.com.ViniSantosDev.Internet.Banking.repository;

import br.com.ViniSantosDev.Internet.Banking.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
