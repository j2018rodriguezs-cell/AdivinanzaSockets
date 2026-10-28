import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {
    private static final int PUERTO = 5000;
    private static final int NUMERO_SECRETO = new Random().nextInt(100) + 1;

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("[SERVIDOR] Iniciando Servidor de Adivinanzas TCP...");
        System.out.println("=================================================");
        System.out.println("[SERVIDOR] (Número secreto generado para pruebas: " + NUMERO_SECRETO + ")");

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("[SERVIDOR] El servidor ha iniciado la escucha en el puerto " + PUERTO + ".");
            int contadorClientes = 0;

            while (true) {
                Socket socketCliente = serverSocket.accept();
                contadorClientes++;
                
                System.out.println("\n[SERVIDOR] ¡Conexión establecida con éxito!");
                System.out.println("[SERVIDOR] Cliente #" + contadorClientes + " conectado desde: " + socketCliente.getRemoteSocketAddress());

                ManejadorCliente manejador = new ManejadorCliente(socketCliente, contadorClientes, NUMERO_SECRETO);
                new Thread(manejador).start();
            }

        } catch (IOException e) {
            System.err.println("[SERVIDOR] Error en el servidor principal: " + e.getMessage());
        }
    }
}