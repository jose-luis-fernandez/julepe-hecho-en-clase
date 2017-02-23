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
     * MÃ©todo que tira una carta aleatoria 
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
     * MÃ©todo que tira una carta "inteligentemente"
     */
    public Carta tirarCartaInteligentemente(Palo paloPrimeraCartaDeLaBaza, 
    Carta cartaQueVaGanando,
    Palo paloQuePinta)
    {
        /**
         * Método que hará que un jugador tire una carta intentando ganar la baza,
         * en caso de que no pueda ganar intentará asistir y si no puede ni ganar ni
         * asistir tirará la peor carta en su mano.
         */
        
        Carta cartaATirar = null; // Variable local tipo Carta que almacenará la carta que tirará el jugador
        Carta peorCarta = null; // Variable local tipo Carta que almacenará la peor carta en la mano.
        Carta mejorCartaParaAsistir = null; // Variable local tipo Carta que almacenará la mejor carta con la que asistir.
        Carta mejorCartatriunfo = null; // Variable local tipo Carta que almacenará la mejor carta triunfo.
        
        if (cartaATirar == null){
            /**
             * Bucle que busca la mejor carta para asistir y la tira.
             */
            
            for (int i = 0; i < cartasQueTieneEnLaMano.length; i++){
                if (cartasQueTieneEnLaMano[i].getPalo() == paloPrimeraCartaDeLaBaza && i == 0){
                    mejorCartaParaAsistir = cartasQueTieneEnLaMano[i];
                }
                
                if (i > 0 && mejorCartaParaAsistir != null && mejorCartaParaAsistir.ganaA(cartasQueTieneEnLaMano[i], paloQuePinta) == false){
                    mejorCartaParaAsistir = cartasQueTieneEnLaMano[i];
                }
            }
            cartaATirar = mejorCartaParaAsistir;
        }
        
        if (cartaATirar == null){
            /**
             * Bucle que, en el caso de que no se pueda tirar ninguna carta que asista
             * buscará el mejor triunfo y lo tirará.
             */
            for (int i = 0; i < cartasQueTieneEnLaMano.length; i++){
                if (cartasQueTieneEnLaMano[i].getPalo() == paloQuePinta && i == 0){
                    mejorCartatriunfo = cartasQueTieneEnLaMano[i];
                }
                
                if (i > 0 && mejorCartatriunfo != null && mejorCartatriunfo.ganaA(cartasQueTieneEnLaMano[i], paloQuePinta) == false){
                    mejorCartatriunfo = cartasQueTieneEnLaMano[i];
                }
                cartaATirar = mejorCartatriunfo;
            }
        }
        
        if (cartaATirar == null){
            /**
             * Bucle que, en el caso de que no pueda asistir ni tirar triunfo
             * buscará la peor carta en la mano y la tira.
             */
            for (int i = 0; i < cartasQueTieneEnLaMano.length; i++){
                if(i == 0){
                    peorCarta = cartasQueTieneEnLaMano[i];
                }
                
                if (i > 0 && peorCarta.ganaA(cartasQueTieneEnLaMano[i], paloQuePinta)){
                    peorCarta = cartasQueTieneEnLaMano[i];
                }
                cartaATirar = peorCarta;
            }
        }
        System.out.println(nombre + " ha tirado " + cartaATirar);
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




