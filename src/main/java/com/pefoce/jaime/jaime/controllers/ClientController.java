package com.pefoce.jaime.jaime.controllers;


import com.pefoce.jaime.jaime.models.Client;
import com.pefoce.jaime.jaime.payload.response.MessageResponse;
import com.pefoce.jaime.jaime.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Client>> getAllClients() {
        try {
            List<Client> clients = clientRepository.findAll();
            if (clients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            // Verificar se já existe um cliente com o mesmo nome ou CPF
            if (clientRepository.existsByNome(client.getNome())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Erro: Nome já está em uso!"));
            }

            if (clientRepository.existsByCpf(client.getCpf())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Erro: CPF já está em uso!"));
            }

            if (clientRepository.existsByEmail(client.getEmail())){
                return  ResponseEntity.badRequest().body(new MessageResponse("Erro: Email já em uso!"));
            }

            // Se não houver duplicatas, salve o cliente
            Client _client = new Client(
                    client.getNome(),
                    client.getCpf(),
                    client.getEmail(),
                    client.getRenda(),
                    client.getTelefone(),
                    client.getDataCriacao()
            );

            clientRepository.save(_client);
            return ResponseEntity.ok(new MessageResponse("Cliente cadastrado com sucesso!"));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") Integer id) {
        try {
            clientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
