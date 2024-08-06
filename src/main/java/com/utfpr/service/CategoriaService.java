package com.utfpr.service;

import com.utfpr.dto.RequestCategoriaDTO;
import com.utfpr.dto.ResponseCategoriaDTO;
import com.utfpr.entity.Categoria;
import com.utfpr.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public ResponseCategoriaDTO inserir(@Valid RequestCategoriaDTO dto) {
        Categoria entity = new Categoria();
        dtoToEntity(dto, entity);
        entity = categoriaRepository.save(entity);
        return new ResponseCategoriaDTO(entity);
    }

    private void dtoToEntity(@Valid RequestCategoriaDTO dto, Categoria entity) {
        entity.setDescCategoria(dto.descCategoria());
    }

    @Transactional(readOnly = true)
    public List<ResponseCategoriaDTO> buscarTodasAsCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(ResponseCategoriaDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ResponseCategoriaDTO buscarPorId(Long id) {
        Categoria entity = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe categoria com o id: " + id));
        return new ResponseCategoriaDTO(entity);
    }

    @Transactional
    public ResponseCategoriaDTO atualizarPorId(Long id, @Valid RequestCategoriaDTO dto) {
        try {
            Categoria entity = categoriaRepository.getReferenceById(id);
            dtoToEntity(dto, entity);
            entity = categoriaRepository.save(entity);
            return new ResponseCategoriaDTO(entity);
        } catch (EntityNotFoundException ex) {
            throw new IllegalArgumentException("Não existe categoria com o id: " + id);
        }
    }

    public void removerPorId(Long id) {
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            new IllegalArgumentException("Não é possível excluir categoria com musica associada!");
        }
    }

    @Transactional(readOnly = true)
    public List<Categoria> getAll() {
        return this.categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> encontrar(Long id) {
        return this.categoriaRepository.findById(id);
    }

    public Categoria salvar(Categoria categoria) {
        try {
            return this.categoriaRepository.saveAndFlush(categoria);
        } catch (Exception ex) {
            return null;
        }
    }
}