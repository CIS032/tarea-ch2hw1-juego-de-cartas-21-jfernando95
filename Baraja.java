/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author Ferchitoo
 */
/// **
// * Un objeto de tipo Deck representa una baraja de cartas. La cubierta
// * es un mazo de póquer regular que contiene 52 cartas regulares y que puede
// * también opcionalmente incluye dos Jokers.
// * /
public class Baraja {

//     * Una matriz de 52 o 54 cartas. Un mazo de 54 cartas contiene dos Jokers,
//     * además de las 52 cartas de un mazo de póker regular.
//     * /
    private Carta[] baraja;


//    / **
//     * Realiza un seguimiento del número de cartas que se han repartido desde
//     * el mazo hasta ahora.
//     * /
    private int cartasUtilizadas;

//    / **
//     * Construye una baraja de póker regular de 52 cartas. Inicialmente, las cartas
//     * están en un orden ordenado. El método shuffle () se puede llamar a
//     * aleatorizar el orden. (Tenga en cuenta que "nuevo Deck ()" es equivalente
//     * a "cubierta nueva (falsa)".)
//     * /
    

    public Baraja() {
        this(false); // Simplemente llama al otro constructor en esta clase.
    }

//    / ** 
//     * Construye un mazo de cartas de póquer, El mazo contiene 
//     * las 52 cartas habituales y, opcionalmente, puede contener dos Comodines 
//     * además de un total de 54 cartas. Inicialmente, las tarjetas 
//     * están ordenadas. Se puede llamar al método shuffle () para 
//     * aleatorizar el orden. 
//     * @param includeJokers si es verdadero, dos comodines están incluidos en el mazo; si es falso,
//     * no hay Jokers en el mazo. 
//     * / 
    public Baraja(boolean incluirJokers) {
        if (incluirJokers)
            baraja = new Carta[54];
        else
            baraja = new Carta[52];
        int cartaCt = 0; // How many cards have been created so far.
        for ( int palo = 0; palo <= 3; palo++ ) {
            for ( int valor = 1; valor <= 13; valor++ ) {
                baraja[cartaCt] = new Carta(valor,palo);
                cartaCt++;
            }
        }
        if (incluirJokers) {
            baraja[52] = new Carta(1,Carta.JOKER);
            baraja[53] = new Carta(2,Carta.JOKER);
        }
        cartasUtilizadas= 0;
    }

    /**
     * Put all the used cards back into the deck (if any), and
     * shuffle the deck into a random order.
     */
    public void barajar() {
        for ( int i = baraja.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Carta temp = baraja[i];
            baraja[i] = baraja[rand];
            baraja[rand] = temp;
        }
        cartasUtilizadas= 0;
    }

//   / ** 
//     * A medida que las cartas se reparten desde el mazo, 
//     disminuye el número de cartas que quedan *. Esta función devuelve la cantidad de tarjetas que
//     * aún quedan en el mazo. El valor de retorno sería 
//     * 52 o 54 (dependiendo de si el mazo incluye comodines) 
//     * cuando se crea el mazo por primera vez o después de que el mazo ha sido 
//     * barajado. Disminuye en 1 cada vez que 
//     se llama al método dealCard () *. 
//     * / 
    public int cartasIzquierda() {
        return baraja.length - cartasUtilizadas;
    }

//    / ** 
//     * Elimina la siguiente carta del mazo y la devuelve. Es ilegal 
//     * llamar a este método si no hay más cartas en el mazo. Puede 
//     * verificar el número de tarjetas restantes llamando a la función cardsLeft (). 
//     * @return la carta que se retira de la baraja.
//     * @throws IllegalStateException si no quedan cartas en el mazo 
//     * / 
    public Carta repartoCartas() {
        if (cartasUtilizadas == baraja.length)
            throw new IllegalStateException("No cards are left in the deck.");
        cartasUtilizadas++;
        return baraja[cartasUtilizadas - 1];
        // Nota de programación: las tarjetas no se eliminan literalmente de la matriz 
        // que representa la plataforma. Solo hacemos un seguimiento de cuántas tarjetas 
        // se han usado.
    }

//   / ** 
//     * Comprobar si el mazo contiene comodines. 
//     * @return true, si este es un mazo de 54 cartas que contiene dos comodines, o falso si 
//     * este es un mazo de 52 cartas que no contiene comodines.
//     * / 
    public boolean tenerJokers() {
        return (baraja.length == 54);
    }

} // end class Deck
