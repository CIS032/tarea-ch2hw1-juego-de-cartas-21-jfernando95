/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Ferchitoo
 */
public class Blackjack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int dinero; // Cantidad de dinero que tiene el usuario. 
        int apuesta;// Cantidad de apuestas de usuario en un juego. 
        boolean usuarioGanador; // ¿El usuario ganó el juego?

        System.out.println("Bienvenido al juego de blackjack");
        System.out.println();

        dinero = 100;  // User starts with $100.

        while (true) {
            System.out.println("Tiene" + dinero + " dolares.");
            do {
                System.out.println("¿Cuántos dólares quieres apostar?  (Ingresa 0 para finalizar.)");
                System.out.print("? ");
                apuesta = TextIO.getlnInt();
                if (apuesta < 0 || apuesta > dinero) {
                    System.out.println("Su respuesta debe estar entre 0 y" + dinero + '.');
                }
            } while (apuesta < 0 || apuesta > dinero);
            if (apuesta == 0) {
                break;
            }
            usuarioGanador = jugarBlackjack();
            if (usuarioGanador) {
                dinero = dinero + apuesta;
            } else {
                dinero = dinero - apuesta;
            }
            System.out.println();
            if (dinero == 0) {
                System.out.println("\"Parece que te has quedado sin dinero!");
                break;
            }
        }

        System.out.println();
        System.out.println("Sales con $" + dinero + '.');

    } // end main()

    /**
     * Let the user play one game of Blackjack, with the computer as dealer.
     *
     * @return true if the user wins the game, false if the user loses.
     */
    public static boolean jugarBlackjack() {

      Baraja baraja;                  // A deck of cards.  A new deck for each game.
      ManodeBlackjack manoCrupier;   // The dealer's hand.
      ManodeBlackjack manoUsuario;     // The user's hand.
      
      baraja = new Baraja();
      manoCrupier= new ManodeBlackjack();
      manoUsuario = new ManodeBlackjack();

      /*  Shuffle the deck, then deal two cards to each player. */
      
      baraja.barajar();
      manoCrupier.agregarCarta( baraja.repartoCartas());
      manoCrupier.agregarCarta( baraja.repartoCartas());
      
      manoUsuario.agregarCarta(baraja.repartoCartas());
      manoUsuario.agregarCarta(baraja.repartoCartas());
      
      System.out.println();
      System.out.println();
      
//        / * Verifica si uno de los jugadores tiene Blackjack (dos cartas que suman un total de 21). 
//         El jugador con Blackjack gana el juego. El distribuidor gana lazos. 
//      * / 
      if (manoCrupier.getValorBlackjack()== 21) {
           System.out.println("\"El crupier tiene la" + manoCrupier.getCarta(0)
                                   + "y la" + manoCrupier.getCarta(1) + ".");
           System.out.println("El usuario tiene la" + manoUsuario.getCarta(0)
                                     + "y la" + manoUsuario.getCarta(1) + ".");
           System.out.println();
           System.out.println("El crupier tiene Blackjack.El crupier gana.");
           return false;
      }
      
      if (manoUsuario.getValorBlackjack()== 21) {
           System.out.println("El crupier tiene la" + manoCrupier.getCarta(0)
                                   + "y la" + manoCrupier.getCarta(1) + ".");
           System.out.println("El usuario tiene la" + manoUsuario.getCarta(0)
                                     + "y la" + manoUsuario.getCarta(1) + ".");
           System.out.println();
           System.out.println("Tienes Blackjack. Tú ganas");
           return true;
      }
      
//     / * Si ninguno de los jugadores tiene Blackjack, juega el juego. Primero, el usuario 
//          tiene la oportunidad de robar cartas (es decir, "golpear"). 
//          El ciclo while finaliza cuando el usuario elige "Stand". Si el usuario supera los 21,
//          el usuario pierde inmediatamente. 
//      * / 
      
      while (true) {
            /* Mostrar tarjetas de usuario, y dejar que el usuario decida continuar o pararse. */
            System.out.println();
            System.out.println();
            System.out.println("Tus cartas son: ");
            for (int i = 0; i < manoCrupier.getCartasContador(); i++) {
                System.out.println("" + manoUsuario.getCarta(i));
            }
            System.out.println("Su total es " + manoUsuario.getValorBlackjack());
            System.out.println();
            System.out.println("El concecionario muestra la " + manoCrupier.getCarta(0));
            System.out.println();
            System.out.println("Continuar (C) o Parar (P)");
            char accionUsuario; //Respuesta del usuario, 'C' o 'P'.
            do {
                accionUsuario = Character.toUpperCase(TextIO.getlnChar());
                if (accionUsuario != 'C' && accionUsuario != 'P') {
                    System.out.print("Responda C o P");
                }
            } while (accionUsuario != 'C' && accionUsuario != 'P');

            /* Si el usuario tiene éxito, el usuario obtiene una tarjeta. Si el usuario está parado,
              el bucle termina (y es el turno del crupier de robar cartas).
             */
            if (accionUsuario == 'P') {
                // Loop ends; el usuario ha terminado de tomar cartas.
                break;
            } else {
                // userAction es 'H'. Dale una tarjeta al usuario.  
                // Si el usuario supera los 21, el usuario pierde.
                Carta nuevaCarta = baraja.repartoCartas();
                manoUsuario.agregarCarta(nuevaCarta);
                System.out.println();
                System.out.println("El usuario acierta");
                System.out.println("Su carta es la " + nuevaCarta);
                System.out.println("Su total es ahora " + manoUsuario.getValorBlackjack());
                if (manoUsuario.getValorBlackjack() > 21) {
                    System.out.println();
                    System.out.println("Has fallado al pasar de 21. Pierdes");
                    System.out.println("La otra carta del concecionario era la " + manoCrupier.getCarta(1));
                    return false;
                }
            }
        }

        /* Si llegamos a este punto, el usuario tiene Stood con 21 o menos. Ahora, es 
         la oportunidad del distribuidor para dibujar. El distribuidor roba las cartas hasta que el 
         total del repartidor sea> 16. Si el repartidor supera los 21, el repartidor pierde. 
         */
        System.out.println();
        System.out.println("Usuario parado");
        System.out.println("Las cartas del crupier son ");
        System.out.println("    " + manoCrupier.getCarta(0));
        System.out.println("    " + manoCrupier.getCarta(1));

        while (manoCrupier.getValorBlackjack()<= 16) {
            Carta nuevaCarta = baraja.repartoCartas();
            System.out.println("Crupier continua y obtiene la " + nuevaCarta);
            manoCrupier.agregarCarta(nuevaCarta);
            if (manoCrupier.getValorBlackjack() > 21) {
                System.out.println();
                System.out.println("Crupier detenido al pasar de 21. Usted gana");
                return true;
            }
        }
        System.out.println("El total del concecionario es " + manoCrupier.getValorBlackjack());
        /* Si llegamos a este punto, ambos jugadores tienen 21 o menos. Nosotros
         puede determinar el ganador comparando los valores de sus manos. */

        System.out.println();
        if (manoCrupier.getValorBlackjack() == manoUsuario.getValorBlackjack()) {
            System.out.println("El crupier gana en un empate. Pierdes");
            return false;
        } else if (manoCrupier.getValorBlackjack() > manoUsuario.getValorBlackjack()) {
            System.out.println("Crupier gana, " + manoCrupier.getValorBlackjack() + " puntos a " + manoUsuario.getValorBlackjack() + ".");
            return false;

        }else{
            System.out.println("Usted gana, " + manoUsuario.getValorBlackjack() + " puntos a " + manoCrupier.getValorBlackjack() + ".");
            return true;
        }}} // end class Blackjack
