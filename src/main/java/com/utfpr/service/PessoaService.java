package com.utfpr.service;

import com.utfpr.entity.Pessoa;
import com.utfpr.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public List<Pessoa> listarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    @Transactional
    public Pessoa inserirPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional(readOnly = false)
    @Modifying
    @Cascade(CascadeType.REMOVE)
    public void removerPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}
