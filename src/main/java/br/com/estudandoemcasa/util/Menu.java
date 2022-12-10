package br.com.estudandoemcasa.util;

import br.com.estudandoemcasa.model.Produto;
import br.com.estudandoemcasa.service.ProdutoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.sql.SQLException;
import java.util.Scanner;

@Log
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    Scanner input = new Scanner(System.in);
    ProdutoService produtoService = new ProdutoService();

    public void menu() throws SQLException {


        System.out.println("Digite uma opção\n\n" +
                "1: Listar Produtos\n2: Buscar Produto por ID\n" +
                "3: Inserir Novo Produto\n4: Remover Produto\n5: Sair");
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

            case 3:

                System.out.println("Digite o nome do Produto\n");
                String nome = input.next();

                input.nextLine();

                System.out.println("Digite a descrição do Produto\n");
                String descricao =  input.nextLine();

                log.info("Id do Produto Inserido: " + produtoService.insertProduto(nome, descricao));
                menu();
                break;
            case 4:

                System.out.println("Digite o ID do Produto\n");
                Integer idPro = input.nextInt();
                log.info("Produto " + idPro + " removido? " + produtoService.deleteProduto(idPro));
                menu();
                break;
            default:
                log.info("Fim do Programa.");
        }
    }

}
