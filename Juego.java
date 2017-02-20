import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Juego que simula el juego del Julepe.
 * 
 * @author Miguel Bayon
 */
public class Juego
{
    private Jugador[] jugadores;
    private Mazo mazo;
    private Palo paloQuePinta;


    /**
     * Constructor de la clase Juego
     *
     * @param numeroJugadores El número de jugadores que van a jugar
     * @param nombreJugadorHumano El nombre del jugador humano
     */
    public Juego(int numeroJugadores, String nombreJugadorHumano)
    {
        mazo = new Mazo();
        jugadores = new Jugador[numeroJugadores];

        ArrayList<String> posiblesNombres = new ArrayList<String>();
        posiblesNombres.add("Pepe");
        posiblesNombres.add("Maria");
        posiblesNombres.add("Juan");
        posiblesNombres.add("Luis");
        posiblesNombres.add("Marcos");
        posiblesNombres.add("Omar"); 
        posiblesNombres.add("Carlos");
        posiblesNombres.add("Azahara");  

        Jugador jugadorHumano = new Jugador(nombreJugadorHumano);
        jugadores[0] = jugadorHumano;
        System.out.println("Bienvenido a esta partida de julepe, " + nombreJugadorHumano);

        Random aleatorio = new Random();
        for (int i = 1; i < numeroJugadores; i++) {
            int posicionNombreElegido = aleatorio.nextInt(posiblesNombres.size());
            String nombreAleatorioElegido = posiblesNombres.get(posicionNombreElegido);
            posiblesNombres.remove(posicionNombreElegido);

            Jugador jugador = new Jugador(nombreAleatorioElegido);
            jugadores[i] = jugador;

        }

        System.out.println("Tus rivales son: ");
        for (int i = 1; i < jugadores.length; i++) {
            System.out.println(jugadores[i].getNombre());
        }
        System.out.println();
        
        jugar();
    }
    
    
    /**
     * Método que reparte 5 cartas a cada uno de los jugadores presentes en
     * la partida y elige un palo para que pinte.
     *
     * @return El palo que pinta tras repartir
     */
    private Palo repartir() 
    {
        mazo.barajar();

        Carta nuevaCarta = null;
        for (int cartaARepartir = 0; cartaARepartir < 5; cartaARepartir++) {            
            for (int jugadorActual = 0; jugadorActual < jugadores.length; jugadorActual++) {
                nuevaCarta = mazo.sacarCarta();
                jugadores[jugadorActual].recibirCarta(nuevaCarta);
            }
        }

        paloQuePinta = nuevaCarta.getPalo();
        switch (paloQuePinta) {
            case OROS:
            System.out.println("Pintan oros");
            break;
            case COPAS:
            System.out.println("Pintan copas");
            break;
            case ESPADAS:
            System.out.println("Pintan espadas");
            break;
            case BASTOS:
            System.out.println("Pintan bastos");
            break;
        }

        return paloQuePinta;           
    }
   


    /**
     * Devuelve la posición del jugador cuyo nombre se especifica como
     * parámetro.
     *
     * @param nombre El nombre del jugador a buscar
     * @return La posición del jugador buscado o -1 en caso de no hallarlo.
     */
    private int encontrarPosicionJugadorPorNombre(String nombre)
    {
        int posicionJugador = -1;
        for (int i = 0; i < jugadores.length; i++){
            if (jugadores[i].getNombre().equals(nombre)){
                posicionJugador = i;
            }
        }
        return posicionJugador;
    }
    
        
    /**
     * Desarrolla una partida de julepe teniendo en cuenta que el mazo y los
     * jugadores ya han sido creados. 
     * 
     * La partida se desarrolla conforme a las normas del julepe con la
     * excepción de que es el usuario humano quien lanza cada vez la primera
     * carta, independientemente de qué usuario haya ganado la baza anterior y,
     * además, los jugadores no humanos no siguen ningún criterio a la hora
     * de lanzar sus cartas, haciéndolo de manera aleatoria.
     * 
     * En concreto, el método de se encarga de:
     * 1. Repartir las cartas a los jugadores.
     * 2. Solicitar por teclado la carta que quiere lanzar el jugador humano.
     * 3. Lanzar una carta por cada jugador no humano.
     * 4. Darle la baza al jugador que la ha ganado.
     * 5. Informar de qué jugador ha ganado la baza.
     * 6. Repetir el proceso desde el punto 2 hasta que los jugadores hayan
     *    tirado todas sus cartas.
     * 7. Informar de cuántas bazas ha ganado el jugador humano.
     * 8. Indicar si el jugador humano "es julepe" (ha ganado menos de dos
     *    bazas) o "no es julepe".
     *
     */
    private void jugar()
    {
        repartir();
        for (int j = 0; j < 5; j++){
            System.out.println("########################################");
            System.out.println("Cartas de :" + jugadores[0].getNombre());
            jugadores[0].verCartasJugador();
            Baza bazaActual = new Baza(jugadores.length, this.paloQuePinta);
            System.out.println("########################################");
            System.out.println(jugadores[0].getNombre() + " tira una carta");
            Scanner sc = new Scanner(System.in);
            String nombreCarta = sc.nextLine();
            Carta cartaTirada = jugadores[0].tirarCarta(nombreCarta);
            while (cartaTirada == null){
                System.out.println("Esa carta no la tienes");
                System.out.println(jugadores[0].getNombre() + " tira una carta");
                sc = new Scanner(System.in);
                nombreCarta = sc.nextLine();
                cartaTirada = jugadores[0].tirarCarta(nombreCarta);
            }
            bazaActual.addCarta(cartaTirada, jugadores[0].getNombre());
            for (int i = 1; i < jugadores.length; i++){
                Carta cartaAleatoria = jugadores[i].tirarCartaAleatoria();
                bazaActual.addCarta(cartaAleatoria, jugadores[i].getNombre());
            }
            String nombreGanador = bazaActual.nombreJugadorQueVaGanandoLaBaza();
            jugadores[encontrarPosicionJugadorPorNombre(nombreGanador)].addBaza(bazaActual);
            System.out.println(bazaActual.nombreJugadorQueVaGanandoLaBaza()+ " ha ganando la baza");
        }
        System.out.println(jugadores[0].getNombre() + " ha ganado " + jugadores[0].getNumeroBazasGanadas() + " bazas.");
        if (jugadores[0].getNumeroBazasGanadas() > 2){
            System.out.println(jugadores[0].getNombre() + " no es julepe");
        }
        else{
            System.out.println(jugadores[0].getNombre() + " es julepe");
        }
    }    
}





















