package com.utfpr.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utfpr.entity.Fone;
import com.utfpr.repository.FoneRepository;

@Service
public class FoneService {

    private final FoneRepository foneRepository;

    public FoneService(FoneRepository foneRepository) {
		super();
		this.foneRepository = foneRepository;
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
            return this.foneRepository.save(fone);
        } catch (Exception ex) {
            return null;
        }
    }
}
