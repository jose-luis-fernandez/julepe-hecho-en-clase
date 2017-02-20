import java.util.Random;
import java.util.ArrayList;
/**
 * Write a description of class Jugador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Jugador
{
    private String nombre;
    private Carta[] cartasQueTieneEnLaMano;
    private int numeroCartasEnLaMano;
    private ArrayList<Baza> bazasGanadas;

    /**
     * Constructor for objects of class Jugador
     */
    public Jugador(String nombreJugador)
    {
        nombre = nombreJugador;
        cartasQueTieneEnLaMano = new Carta[5];
        numeroCartasEnLaMano = 0;   
        bazasGanadas = new ArrayList<Baza>();
    }

    /**
     * Metodo que hace que el jugador reciba una carta
     */
    public void recibirCarta(Carta cartaRecibida)
    {
        if (numeroCartasEnLaMano < 5) {
            cartasQueTieneEnLaMano[numeroCartasEnLaMano] = cartaRecibida;
            numeroCartasEnLaMano++;
        }
    }

    /**
     * Metodo que muestra las cartas del jugador por pantalla
     */
    public void verCartasJugador()
    {
        for (Carta cartaActual : cartasQueTieneEnLaMano) {
            if (cartaActual != null) {
                System.out.println(cartaActual);
            }
        }
    }

    /**
     * Metodo que devuelve el nombre del jugador
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Metodo que devuelve la carta especificada como parametro si
     * el jugador dispone de ella y simula que se lanza a la mesa
     */    
    public Carta tirarCarta(String nombreCarta)
    {
        Carta cartaTirada = null;

        if (numeroCartasEnLaMano > 0) {

            int cartaActual = 0;
            boolean buscando = true;
            while (cartaActual < cartasQueTieneEnLaMano.length && buscando) {
                if (cartasQueTieneEnLaMano[cartaActual] != null) {                                 
                    if (nombreCarta.equals(cartasQueTieneEnLaMano[cartaActual].toString())) {
                        buscando = false;
                        cartaTirada = cartasQueTieneEnLaMano[cartaActual];
                        cartasQueTieneEnLaMano[cartaActual] = null;
                        numeroCartasEnLaMano--;
                        System.out.println(nombre + " ha tirado " + cartaTirada);
                    }
                }
                cartaActual++;
            }

        }
        return cartaTirada;
    }

    /**
     * Método que tira una carta aleatoria 
     */
    public Carta tirarCartaAleatoria() 
    {
        Carta cartaTirada = null;

        if (numeroCartasEnLaMano > 0) {
            Random aleatorio = new Random();

            boolean elJugadorHaTiradoUnaCarta = false;
            while (elJugadorHaTiradoUnaCarta == false) {
                int posicionAleatoria = aleatorio.nextInt(5);
                if (cartasQueTieneEnLaMano[posicionAleatoria] != null) {
                    cartaTirada = cartasQueTieneEnLaMano[posicionAleatoria];
                    cartasQueTieneEnLaMano[posicionAleatoria] = null;
                    numeroCartasEnLaMano--;
                    System.out.println(nombre + " ha tirado " + cartaTirada);
                    elJugadorHaTiradoUnaCarta = true;
                }
            }

        }

        return cartaTirada;
    }

    /**
     * Método que tira una carta "inteligentemente"
     */
    public Carta tirarCartaInteligentemente(int paloPrimeraCartaDeLaBaza, 
    Carta cartaQueVaGanando,
    int paloQuePinta)
    {
         Carta cartaATirar = null;
//         boolean cartasJugadorPaloQuePinta = false;
//         Carta peorCartaQuePintaEnlaMano = null;
//         Carta mejorCartaQuePintaEnlaMano = null;
//         for (int i = 0; i < cartasQueTieneEnLaMano.length; i++){
//             if (cartasQueTieneEnLaMano[i].getPalo() == paloQuePinta){
//                 cartaATirar = cartasQueTieneEnLaMano[i];
//                 cartasJugadorPaloQuePinta = true;
//                 mejorCartaQuePintaEnlaMano = cartasQueTieneEnLaMano[i];
//                 peorCartaQuePintaEnlaMano = cartasQueTieneEnLaMano[i];
//                 if (cartasQueTieneEnLaMano[i].getPalo() == paloQuePinta && 
//                 cartaJugadorPaloQuePinta != null && cartasQueTieneEnLaMano[i].ganaA(cartaQueVaGanando, paloQuePinta)){
//                     cartaATirar = cartasQueTieneEnLaMano[i];
//                     mejorCartaQuePintaEnlaMano = cartasQueTieneEnLaMano[i];
//                     if (!cartasQueTieneEnLaMano[i].ganaA(peorCartaQuePintaEnlaMano, paloQuePinta)){
//                         peorCartaQuePintaEnlaMano = cartasQueTieneEnLaMano[i];
//                     }
//                 }
//             }
//         }
//         
//         if (cartaQueVaGanando.getPalo() == paloQuePinta){
//             if (cartasJugadorPaloQuePinta == true){
//             
//             }
//         }
// 
         return cartaATirar;
    }

    /**
     * Metodo que hace que jugador recoja una baza ganada
     */
    public void addBaza(Baza bazaGanada)
    {
        bazasGanadas.add(bazaGanada);
    }

    /**
     * Metodo que devuelve el numero de bazas ganadas por el jugador hasta
     * el momento
     */
    public int getNumeroBazasGanadas() 
    {
        return bazasGanadas.size();
    }

}







