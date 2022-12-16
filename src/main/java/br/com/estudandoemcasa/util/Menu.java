package br.com.estudandoemcasa.util;

import br.com.estudandoemcasa.model.Produto;
import br.com.estudandoemcasa.service.ProdutoService;
import lombok.extern.java.Log;

import java.sql.SQLException;
import java.util.Scanner;

@Log
public class Menu {

    Scanner input = new Scanner(System.in);
    ProdutoService produtoService = new ProdutoService();

    public Menu() throws SQLException {
        /**
         *
         * @throws SQLException
         */
    }

    public void menu() throws SQLException {

        System.out.println("Digite uma opção\n\n" +
                "1: Listar Produtos\n" +
                "2: Buscar Produto por ID\n" +
                "3: Inserir Novo Produto\n" +
                "4: Remover Produto\n" +
                "5: Sair");

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

    private void getProd() throws SQLException {
        System.out.println("Digite o ID do Produto\n");
        Integer id = input.nextInt();
        log.info("Produto: " + produtoService.getProduto(id).toString());
        menu();
    }

    private void insereProd() throws SQLException {
        System.out.println("Digite o nome do Produto\n");
        String nome = input.next();

        input.nextLine();

        System.out.println("Digite a descrição do Produto\n");
        String descricao = input.nextLine();

        log.info("Id do Produto Inserido: " + produtoService.insertProduto(nome, descricao));
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
