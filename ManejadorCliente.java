import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    private Socket socket;
    private int idCliente;
    private int numeroSecreto;

    public ManejadorCliente(Socket socket, int idCliente, int numeroSecreto) {
        this.socket = socket;
        this.idCliente = idCliente;
        this.numeroSecreto = numeroSecreto;
    }

    @Override
    public void run() {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            salida.println("¡Bienvenido al Juego de Adivinanzas! Adivina el número entre 1 y 100. Escribe 'SALIR' para terminar.");

            String mensajeCliente;
            while ((mensajeCliente = entrada.readLine()) != null) {
                System.out.println("\n[SERVIDOR] Información recibida del Cliente #" + idCliente + ": \"" + mensajeCliente + "\"");

                if (mensajeCliente.equalsIgnoreCase("SALIR")) {
                    salida.println("Conexión finalizada por solicitud del cliente. ¡Hasta pronto!");
                    System.out.println("[SERVIDOR] El cliente #" + idCliente + " ha decidido salir.");
                    break;
                }

                try {
                    int intento = Integer.parseInt(mensajeCliente.trim());
                    String respuesta;

                    if (intento < numeroSecreto) {
                        respuesta = "Respuesta del Servidor: El número secreto es MAYOR que " + intento + ". ¡Sigue intentando!";
                        salida.println(respuesta);
                        System.out.println("[SERVIDOR] Enviado al Cliente #" + idCliente + ": pista (es mayor).");
                    } else if (intento > numeroSecreto) {
                        respuesta = "Respuesta del Servidor: El número secreto es MENOR que " + intento + ". ¡Sigue intentando!";
                        salida.println(respuesta);
                        System.out.println("[SERVIDOR] Enviado al Cliente #" + idCliente + ": pista (es menor).");
                    } else {
                        respuesta = "Respuesta del Servidor: ¡FELICIDADES! ¡Has adivinado el número secreto!";
                        salida.println(respuesta);
                        System.out.println("[SERVIDOR] ¡El Cliente #" + idCliente + " ha acertado el número secreto!");
                        break;
                    }

                } catch (NumberFormatException e) {
                    salida.println("Respuesta del Servidor: Entrada inválida. Por favor, ingresa un número entero o 'SALIR'.");
                    System.out.println("[SERVIDOR] Advertencia: El Cliente #" + idCliente + " envió un formato no numérico.");
                }
            }

        } catch (IOException e) {
            System.err.println("[SERVIDOR] Error de comunicación con el Cliente #" + idCliente + ": " + e.getMessage());
        } finally {
            try {
                socket.close();
                System.out.println("[SERVIDOR] Conexión cerrada y recursos liberados para el Cliente #" + idCliente + ".");
            } catch (IOException e) {
                System.err.println("[SERVIDOR] Error al cerrar el socket: " + e.getMessage());
            }
        }
    }
}