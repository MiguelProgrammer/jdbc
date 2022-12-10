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
    }

    public void menu() throws SQLException {


        System.out.println("Digite uma opção\n\n" +
                "1: Listar Produtos\n2: Buscar Produto por ID\n3: Sair");
        Integer escolhe = input.nextInt();


        switch (escolhe) {

            case 1:
                for (Produto produto : produtoService.list()) {
                    log.info("Produto: " + produto);
                }
                menu();
                break;

            case 2:
                System.out.println("Digite o ID do Produto\n");
                Integer id = input.nextInt();
                Produto produto = produtoService.getProduto(id);
                log.info("Produto: " + produto);
                menu();
                break;
            default:
                log.info("Fim do Programa.");
        }
    }

}
