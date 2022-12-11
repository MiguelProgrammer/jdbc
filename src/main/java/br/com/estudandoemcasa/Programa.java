package br.com.estudandoemcasa;

import br.com.estudandoemcasa.util.Menu;
import lombok.extern.java.Log;

@Log
public class Programa {

    public static void main(String[] args) {

        try {

            Menu menu = new Menu();
            menu.menu();

        } catch (Exception e) {
            log.info("Programa encerrado.");
        }

    }
}
