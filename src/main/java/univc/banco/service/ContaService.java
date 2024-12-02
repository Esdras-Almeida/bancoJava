package univc.banco.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import univc.banco.entity.Conta;
import univc.banco.repository.ContaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public List<Conta> findAll() {
        return contaRepository.findAll();
    }

    public Conta findById(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
    }
    
    public Conta save(Conta conta) {
        if (conta.getId() != null && contaRepository.existsById(conta.getId())) {
            throw new RuntimeException("Conta com o ID " + conta.getId() + " já existe.");
        }
        return contaRepository.save(conta);
    }

    public Conta update(Long id, Conta conta) {
        Conta existente = findById(id);

        return contaRepository.save(existente);
    }

    public void delete(Long id) {
        contaRepository.deleteById(id);
    }
    
    @Transactional
    public Conta depositar(Long id, double valor) {
        Optional<Conta> contaOpt = contaRepository.findById(id);
        if (contaOpt.isPresent()) {
            Conta conta = contaOpt.get();
            conta.setSaldo(conta.getSaldo() + valor);
            return contaRepository.save(conta);
        } else {
            throw new RuntimeException("Conta não encontrada");
        }
    }
    
    @Transactional
    public Conta sacar(Long id, double valor) {
    Optional<Conta> contaOpt = contaRepository.findById(id);
    if (contaOpt.isPresent()) {
        Conta conta = contaOpt.get();
        
        if (conta.getTipoConta() == Conta.TipoConta.ES) {
            if (conta.getSaldo() + conta.getCondicaoEspecial() >= valor) {
                conta.setSaldo(conta.getSaldo() - valor);
                return contaRepository.save(conta);
            } else {
                throw new RuntimeException("Saldo insuficiente, incluindo condição especial");
            }
        } else {
            if (conta.getSaldo() >= valor) { 
                conta.setSaldo(conta.getSaldo() - valor);
                return contaRepository.save(conta);
            } else {
                throw new RuntimeException("Saldo insuficiente");
            }
        }
    } else {
        throw new RuntimeException("Conta não encontrada");
    }
}
}