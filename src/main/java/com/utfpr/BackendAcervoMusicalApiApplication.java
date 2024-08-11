package com.utfpr;

import com.utfpr.dto.ResponseCategoriaDTO;
import com.utfpr.entity.Categoria;
import com.utfpr.service.CategoriaService;
import com.utfpr.service.MusicaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendAcervoMusicalApiApplication {
    @Autowired
    private MusicaService musicaService;

    private static final Logger log = LoggerFactory.getLogger(BackendAcervoMusicalApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendAcervoMusicalApiApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(CategoriaService categoriaService) {
        return (arg) -> {

            log.info("=======Listagem de todas as categorias");
            for (ResponseCategoriaDTO c : categoriaService.buscarTodasAsCategorias()) {
                log.info(c.toString());
            }
        };
    }
}
