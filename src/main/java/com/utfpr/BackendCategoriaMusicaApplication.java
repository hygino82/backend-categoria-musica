package com.utfpr;

import com.utfpr.entity.*;
import com.utfpr.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendCategoriaMusicaApplication {
    private static final Logger log = LoggerFactory.getLogger(BackendCategoriaMusicaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendCategoriaMusicaApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(CategoriaService categoriaService, MusicaService musicaService, PessoaService pessoaService, FoneService foneService, GravacaoService gravacaoService) {
        return (arg) -> {

			/*log.info("");
			log.info("");
			log.info("=======Listagem de todas as musicas");
			for (MusicaDTO m : musicaService.listarTodasMusicas()) {
				log.info(m.toString());
			}*/

            /*log.info("");
            log.info("");
            log.info("=======Listagem de todas as categorias");
            for (Categoria c : categoriaService.listarTodasCategorias()) {
                log.info(c.toString());
            }*/


			/*log.info("");
			log.info("");
			log.info("=======Chamar Stored Procedure");
			musicaService.adicionarTempo(1000);


			log.info("");
			log.info("");
			log.info("=======Listagem de todas as musicas após executar Procedure");
			for (MusicaDTO m : musicaService.listarTodasMusicas()) {
				log.info(m.toString());
			}*/

			/*log.info("");
			log.info("");
			log.info("=======Chamar Stored Procedure subtrair tempo");
			musicaService.subtrairTempo(1000);*/


			/*log.info("");
			log.info("");
			log.info("=======Listagem de todas as musicas após executar Procedure");
			for (MusicaDTO m : musicaService.listarTodasMusicas()) {
				log.info(m.toString());
			}*/

          /*  log.info("");
            log.info("");
            log.info("=======Listagem de todas as pessoas");
            for (var pessoa : pessoaService.listarTodasPessoas()) {
                log.info(pessoa.toString());
            }*/

            /*log.info("");
            log.info("");
            log.info("=======Listagem de todas os telefones");
            for (var obj : foneService.listarTodosTelefones()) {
                log.info(obj.toString());
            }*/


            /*log.info("");
            log.info("");
            log.info("=======Inserir Pessoa");
            Pessoa p = new Pessoa("Godofredo");
            System.out.println(pessoaService.inserirPessoa(p));
            */
            /*
            log.info("");
            log.info("");
            long id = 12L;
            log.info("=======Remover pessoa Pessoa com o id: " + id);
            pessoaService.removerPessoa(id);*/

            /*log.info("");
            log.info("=======Inserir Pessoa com telefone");
            Pessoa p = new Pessoa("Melania");
            Fone f = new Fone("321486475", 'F');
            System.out.println(foneService.inserirPessoaTelefone(p, f));*/

            /*log.info("");
            log.info("");
            long id = 11L;
            log.info("=======Remover telefone com o id: {}", id);
            foneService.removerTelefone(id);*/

            /*log.info("");
            log.info("=======Inserir gravacao");
            Categoria categoria = new Categoria("Gaucha");
            Musica musica = new Musica("Luz do meu rancho", 180);
            Cantor cantor = new Cantor("Porca Veia", "Brasil");
            Gravadora gravadora = new Gravadora("ACIT", "Brasil");
            System.out.println(gravacaoService.novaGravacao(categoria, musica, cantor, gravadora));*/

            log.info("");
            log.info("");
            log.info("=======Listagem de todas as gravações");
            for (var obj : gravacaoService.buscarTodasGravacoes()) {
                log.info(obj.toString());
            }

            log.info("");
            log.info("");
            log.info("=======Listagem gravação por Id");
            final var gravacao = gravacaoService.buscarGravacaoPorId(1L);
            log.info(gravacao.toString());
        };
    }
}
