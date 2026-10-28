import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("[CLIENTE] Iniciando aplicación de cliente...");
        System.out.println("=================================================");
        System.out.println("[CLIENTE] Intentando establecer conexión con el servidor en " + HOST + ":" + PUERTO + "...");

        try (
            Socket socket = new Socket(HOST, PUERTO);
            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salidaServidor = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("[CLIENTE] ¡Conexión establecida exitosamente con el servidor!");

            String bienvenida = entradaServidor.readLine();
            System.out.println("\n" + bienvenida);

            String entradaUsuario;
            do {
                System.out.print("\nIntroduce tu número (o 'SALIR'): ");
                entradaUsuario = scanner.nextLine();

                salidaServidor.println(entradaUsuario);

                String respuestaServidor = entradaServidor.readLine();
                if (respuestaServidor != null) {
                    System.out.println(respuestaServidor);
                    if (respuestaServidor.contains("FELICIDADES") || respuestaServidor.contains("finalizada")) {
                        break;
                    }
                }

            } while (!entradaUsuario.equalsIgnoreCase("SALIR"));

            System.out.println("[CLIENTE] La comunicación ha finalizado.");

        } catch (IOException e) {
            System.err.println("[CLIENTE] Error al conectar con el servidor: " + e.getMessage());
        }
    }
}