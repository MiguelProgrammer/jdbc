package br.com.estudandoemcasa.util;

import br.com.estudandoemcasa.model.Categoria;
import br.com.estudandoemcasa.model.Produto;
import br.com.estudandoemcasa.service.CategoriaService;
import br.com.estudandoemcasa.service.ProdutoService;
import lombok.extern.java.Log;

import java.sql.SQLException;
import java.util.Scanner;

@Log
public class Menu {

    Scanner input = new Scanner(System.in);
    ProdutoService produtoService = new ProdutoService();
    CategoriaService categoriaService = new CategoriaService();

    public Menu() throws SQLException {
        /**
         *
         * @throws SQLException
         */
    }

    public void menu() throws SQLException {

        System.out.println("Digite uma opção\n\n" +
                "------- PRODUTOS -------\n" +
                "1: Listar Produtos\n" +
                "2: Buscar Produto por ID\n" +
                "3: Inserir Novo Produto\n" +
                "4: Remover Produto\n\n" +
                "------- CATEGORIAS -------\n" +
                "5: Listar Categorias e Produtos\n" +
                "6: Buscar Categoria e Produtos\n" +
                "7: Inserir Nova Categoria\n" +
                "8: Remover Categoria\n" +
                "0: Sair");

        switch (input.nextInt()) {

            case 1:
                listProd();
                break;

            case 2:
                getProd();
                break;

            case 3:
                insereProd();
                break;

            case 4:
                deleteProd();
                break;

            case 5:
                listCat();
                break;
            case 6:
                getCat();
                break;

            default:
                log.info("Fim do Programa.");
                System.runFinalization();
                break;
        }
    }

    private void listProd() throws SQLException {
        for (Produto produto : produtoService.list()) {
            log.info("Produto: " + produto);
        }
        menu();
    }

    private void listCat() throws SQLException {
        for (Categoria categoria : categoriaService.list()) {
            log.info("Categorias e Produtos: " + categoria);
        }
        menu();
    }

    private void getProd() throws SQLException {
        System.out.println("Digite o ID do Produto\n");
        Integer id = input.nextInt();
        log.info("Produto: " + produtoService.getProduto(id).toString());
        menu();
    }
    private void getCat() throws SQLException {
        System.out.println("Digite o ID da Categoria\n");
        Integer id = input.nextInt();
        log.info("Categoria: " + categoriaService.getCategoria(id).toString());
        log.info("Produtos da Categoria: " + categoriaService.getCategoria(id).toString());
        categoriaService.getCategoria(id).getProdutos().stream().forEach(System.out::println);
        menu();
    }

    private void insereProd() throws SQLException {
        System.out.println("Digite o nome do Produto\n");
        String nome = input.next();

        input.nextLine();

        System.out.println("Digite a descrição do Produto\n");
        String descricao = input.nextLine();

        System.out.println("Digite o id da categoria do Produto\n");
        Integer catrgoriaId = input.nextInt();

        log.info("Id do Produto Inserido: " + produtoService.insertProduto(nome, descricao, catrgoriaId));
        menu();
    }

    private void deleteProd() throws SQLException {
        System.out.println("Digite o ID do Produto\n");
        Integer idPro = input.nextInt();
        produtoService.deleteProduto(idPro);
        this.listProd();
        menu();
    }

}
