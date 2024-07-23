package com.utfpr.service;

import com.utfpr.entity.Fone;
import com.utfpr.entity.Pessoa;
import com.utfpr.repository.FoneRepository;
import com.utfpr.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoneService {

    private final FoneRepository foneRepository;

    @Transactional(readOnly = true)
    public List<Fone> listarTodosTelefones() {
        return foneRepository.findAll();
    }
}
