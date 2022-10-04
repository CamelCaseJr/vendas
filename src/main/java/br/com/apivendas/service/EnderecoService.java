package br.com.apivendas.service;

import br.com.apivendas.model.entity.Endereco;
import br.com.apivendas.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }


    public List<Endereco> findyAll() {
        return enderecoRepository.findAll();
    }

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    public void deletar(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }
}
