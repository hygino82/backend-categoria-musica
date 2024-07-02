package com.utfpr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.utfpr.entity.Categoria;
import com.utfpr.entity.Musica;
import com.utfpr.service.CategoriaService;
import com.utfpr.service.MusicaService;

@SpringBootApplication
public class BackendCategoriaMusicaApplication {
	private static final Logger log = LoggerFactory.getLogger(BackendCategoriaMusicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendCategoriaMusicaApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(CategoriaService categoriaService, MusicaService musicaService) {
		return (arg) -> {

			log.info("");
			log.info("");
			log.info("=======Listagem de todas as músicas");
			for (Musica m : musicaService.listarTodasMusicas()) {
				log.info(m.toString());
			}

			log.info("");
			log.info("");
			log.info("=======Listagem de todas as categorias");
			for (Categoria c : categoriaService.listarTodasCategorias()) {
				log.info(c.toString());
			}

		};
	}
}
