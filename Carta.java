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
/**
 * An object of type Card represents a playing card from a
 * standard Poker deck, including Jokers.  The card has a suit, which
 * can be spades, hearts, diamonds, clubs, or joker.  A spade, heart,
 * diamond, or club has one of the 13 values: ace, 2, 3, 4, 5, 6, 7,
 * 8, 9, 10, jack, queen, or king.  Note that "ace" is considered to be
 * the smallest value.  A joker can also have an associated value; 
 * this value can be anything and can be used to keep track of several
 * different jokers.
 */
public class Carta {
    public final static int ESPADAS = 0;   // Códigos para los 4 palos, más Joker.
    public final static int CORAZONES = 1;
    public final static int DIAMANTES = 2;
    public final static int TREBOL = 3;
    public final static int JOKER = 4;

    public final static int AS = 1;      // Códigos para las tarjetas no numéricas.
    public final static int JACK = 11;   // Las tarjetas 2 a 10 tienen su
    public final static int REINA = 12;   // valores numéricos para sus códigos.
    public final static int REY = 13;

// 
//     El juego de esta carta, una de las constantes SPADES, HEARTS, DIAMONDS,
//     * CLUBS, o JOKER. La demanda no puede ser cambiada después de que la tarjeta es
//     * construido.
//     * /
    private final int palo; 

//   
//     * El valor de la tarjeta. Para una carta normal, este es uno de los valores
//     * 1 a 13, con 1 representando ACE. Para un JOKER, el valor
//     * puede ser cualquier cosa. El valor no se puede cambiar después de la tarjeta
//     * esta construido.
//     * /
    private final int valor;
//
//     
//   Crea un Joker, con 1 como el valor asociado. (Tenga en cuenta que
//   "nueva Tarjeta ()" es equivalente a "nueva Tarjeta (1, Tarjeta.JADORA)".)
    public Carta() {
        palo = JOKER;
        valor = 1;
    }

//    
//     * Crea una carta con un palo y valor especificados.
//     * @param theValue el valor de la nueva tarjeta. Para una tarjeta regular (no bromista),
//     * el valor debe estar en el rango de 1 a 13, donde 1 representa un As.
//     * Puede usar las constantes Card.ACE, Card.JACK, Card.QUEEN y Card.KING.  
//     * Para un Joker, el valor puede ser cualquier cosa.
//     * @param theSuit el traje de la nueva tarjeta. Este debe ser uno de los valores
//     * Card.SPADES, Card.HEARTS, Card.DIAMONDS, Card.CLUBS o Card.JOKER.
//     * @throws IllegalArgumentException si los valores de los parámetros no están en el
//     * rangos permisibles

    public Carta(int elValor, int elPalo) {
        if (elPalo != ESPADAS && elPalo != CORAZONES && elPalo != DIAMANTES && 
                elPalo != TREBOL && elPalo != JOKER)
            throw new IllegalArgumentException("Traje de naipes ilegal");
        if (elPalo != JOKER && (elValor < 1 || elValor > 13))
            throw new IllegalArgumentException("valor de naipes ilegal");
        valor = elValor;
        palo = elPalo;
    }

// 
//     * Devuelve la demanda de esta tarjeta.
//     * @ devuelve el traje, que es una de las constantes Card.SPADES, 
//     * Card.HEARTS, Card.DIAMONDS, Card.CLUBS o Card.JOKER
 
    public int getPalo() {
        return palo;
    }

    /**
     * Returns the value of this card.
     * @return the value, which is one of the numbers 1 through 13, inclusive for
     * a regular card, and which can be any value for a Joker.
     */
    public int getValor() {
        return valor;
    }

/// **
//     * Devuelve una representación de Cadena del palo de la carta.
//     * @return una de las cadenas "Picas", "Corazones", "Diamantes", "Clubes"
//     * o "Joker".
//     * /
    public String getPaloComoString() {
        switch (palo) {
        case ESPADAS:   return "ESPADAS";
        case CORAZONES:   return "CORAZONES";
        case DIAMANTES: return "DIAMANTES";
        case TREBOL:    return "TREBOLES";
        default:       return "JOKER";
        }
    }

//    / **
//     * Devuelve una representación de cadena del valor de la tarjeta.
//     * @return para una carta regular, una de las cuerdas "Ace", "2",
//     * "3", ..., "10", "Jack", "Queen" o "King". Para un Joker, el
//     * la cadena siempre es numérica.
//     * /
    public String getValorComoString() {
        if (palo == JOKER)
            return "" + valor;
        else {
            switch ( valor ) {
            case 1:   return "As";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case 11:  return "Jack";
            case 12:  return "Reina";
            default:  return "Rey";
            }
        }
    }

//     / **
//     * Devuelve una representación de cadena de esta tarjeta, incluidos ambos
//     * su palo y su valor (excepto que para un Joker con valor 1,
//     * el valor de retorno es solo "Joker"). Valores de retorno de muestra
//     * son: "Reina de Corazones", "10 de Diamantes", "As de Picas",
//     * "Joker", "Joker # 2"
//     * /
    public String toString() {
        if (palo == JOKER) {
            if (valor == 1)
                return "Joker";
            else
                return "Joker #" + valor;
        }
        else
            return getValorComoString() + " de " + getPaloComoString();
    }


} // tarjeta de clase final
