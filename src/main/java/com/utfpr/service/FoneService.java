package com.utfpr.service;

import com.utfpr.entity.Fone;
import com.utfpr.entity.Pessoa;
import com.utfpr.repository.FoneRepository;
import com.utfpr.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FoneService {

    private final FoneRepository foneRepository;
    private final PessoaRepository pessoaRepository;

    public FoneService(FoneRepository foneRepository, PessoaRepository pessoaRepository) {
        this.foneRepository = foneRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public List<Fone> listarTodosTelefones() {
        return foneRepository.findAll();
    }

    @Transactional(readOnly = false)
    @Modifying
    @Cascade(CascadeType.REMOVE)
    public void removerTelefone(Long id) {
        foneRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Fone> encontrar(Long id) {
        return this.foneRepository.findById(id);
    }

    public Fone salvar(Fone fone) {
        try {
            return this.foneRepository.saveAndFlush(fone);
        } catch (Exception ex) {
            return null;
        }
    }
}
