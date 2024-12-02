package univc.banco.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import univc.banco.entity.Conta;
import univc.banco.service.ContaService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public List<Conta> findAll() {
        return contaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Conta> save(@RequestBody Conta conta) {
        try {
            return ResponseEntity.ok(contaService.save(conta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.update(id, conta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @PostMapping("/{id}/depositar")
    public ResponseEntity<Conta> depositar(@PathVariable Long id, @RequestParam double valor) {
        System.out.println("Endpoint /" + id + "/depositar chamado.");
    System.out.println("ID recebido: " + id);
    System.out.println("Valor recebido: " + valor);
        try {
            Conta contaAtualizada = contaService.depositar(id, valor);
            return ResponseEntity.ok(contaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{id}/sacar")
    public ResponseEntity<Conta> sacar(@PathVariable Long id, @RequestParam double valor) {
        try {
            Conta contaAtualizada = contaService.sacar(id, valor);
            return ResponseEntity.ok(contaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}