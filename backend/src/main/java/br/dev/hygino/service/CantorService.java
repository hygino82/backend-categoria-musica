package br.dev.hygino.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestCantorDTO;
import br.dev.hygino.dto.ResponseCantorDTO;
import br.dev.hygino.entity.Cantor;
import br.dev.hygino.reporisory.CantorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class CantorService {

    private final CantorRepository cantorRepository;

    public CantorService(CantorRepository cantorRepository) {
        this.cantorRepository = cantorRepository;
    }

    @Transactional
    public ResponseCantorDTO inserir(@Valid RequestCantorDTO dto) {
        Cantor entity = new Cantor();
        dtoToEntity(dto, entity);
        entity = cantorRepository.save(entity);
        return new ResponseCantorDTO(entity);
    }

    private void dtoToEntity(@Valid RequestCantorDTO dto, Cantor entity) {
        entity.setNome(dto.nome());
        entity.setPais(dto.pais());
    }

    @Transactional(readOnly = true)
    public List<ResponseCantorDTO> buscarTodos() {
        return cantorRepository.findAll()
                .stream()
                .map(ResponseCantorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ResponseCantorDTO buscarPorId(Long id) {
        final var entity = cantorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe Cantor com o id: " + id));
        return new ResponseCantorDTO(entity);
    }

    @Transactional
    public ResponseCantorDTO atualizarPorId(Long id, @Valid RequestCantorDTO dto) {
        try {
            Cantor entity = cantorRepository.getReferenceById(id);
            dtoToEntity(dto, entity);
            entity = cantorRepository.save(entity);
            return new ResponseCantorDTO(entity);
        } catch (EntityNotFoundException ex) {
            throw new IllegalArgumentException("Não existe Cantor com o id: " + id);
        }
    }

    public void remover(Long id) {
        try {
            cantorRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Não pode remover cantor associado a uma gravação");
        }
    }
}
