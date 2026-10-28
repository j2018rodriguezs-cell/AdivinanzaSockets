# 🎮 Juego de Adivinanzas Concurrente en Red (Sockets TCP en Java)

Proyecto desarrollado para la **Unidad 2: Concurrencia y Network** de la asignatura. Implementa una aplicación interactiva bajo arquitectura cliente-servidor utilizando Sockets TCP en Java y atención multihilo para garantizar concurrencia real.

---

## 👨‍💻 Integrantes
* **Jonathan David Rodríguez Suárez**

---

## 🚀 Descripción de la Aplicación
La aplicación consiste en un **Juego de Adivinanzas en Red** (Opción 1 de la actividad). 

* El **Servidor** genera automáticamente un número secreto aleatorio entre `1` y `100` al iniciar.
* Múltiples **Clientes** pueden conectarse de manera simultánea.
* Cada cliente envía sus intentos de adivinanza desde su propia consola.
* El servidor procesa los intentos de forma independiente y responde a cada cliente si el número secreto es **mayor**, **menor** o si ha **ganado el juego**.
* Los clientes pueden desconectarse en cualquier momento enviando la palabra `SALIR`.

---

## 🛠️ Tecnologías y Arquitectura

* **Lenguaje de Programación:** Java (JDK 17+)
* **Entorno de Desarrollo (IDE):** Visual Studio Code
* **Protocolo de Red:** TCP (`java.net.ServerSocket` y `java.net.Socket`)
* **Manejo de Concurrencia:** Hilos independientes por cliente mediante la interfaz `Runnable` y la clase `Thread`

### Estructura de Archivos del Código Fuente

    AdivinanzaSockets/
    ├── Servidor.java          # Inicia el ServerSocket y gestiona la escucha en el puerto.
    ├── ManejadorCliente.java  # Clase que implementa Runnable; atiende a cada cliente en un hilo dedicado.
    └── Cliente.java           # Aplicación cliente que conecta al socket y gestiona la entrada/salida del usuario.

---

## ⚙️ Configuración de Red
* **Host:** `localhost` (127.0.0.1)
* **Puerto:** `5000`

---

## 🧵 Implementación de la Concurrencia

La atención simultánea de múltiples clientes se logra de la siguiente manera:
1. El archivo `Servidor.java` ejecuta un bucle continuo `while (true)` con `serverSocket.accept()`.
2. Cada vez que llega un intento de conexión entrante, el servidor acepta el socket.
3. Se instancia una clase `ManejadorCliente` asignada a ese socket particular.
4. Se envuelve el manejador en un nuevo hilo (`new Thread(manejador).start()`).
5. De esta forma, cada cliente corre en su propio flujo de ejecución sin bloquear a otros clientes ni detener el bucle principal del servidor.

---

## 💻 Instrucciones de Compilación y Ejecución

### Requisitos Previos
* Tener instalado el Java Development Kit (JDK 17 o superior).
* Visual Studio Code con la extensión *Extension Pack for Java*.

### Pasos para Ejecutar
1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/j2018rodriguezs-cell/AdivinanzaSockets.git](https://github.com/j2018rodriguezs-cell/AdivinanzaSockets.git)
2. **Iniciar el Servidor:**
   * Abre `Servidor.java` en Visual Studio Code y presiona el botón **Play (▶️)**.
   * Deberás ver en consola: `[SERVIDOR] El servidor ha iniciado la escucha en el puerto 5000.`

3. **Ejecutar el Primer Cliente:**
   * Abre una nueva pestaña de terminal en VS Code.
   * Abre `Cliente.java` y presiona **Play (▶️)**.
   * Ingresa tu primer intento de número en la consola.

4. **Probar la Concurrencia (Segundo Cliente):**
   * Abre una **segunda pestaña de terminal independiente**.
   * Vuelve a ejecutar `Cliente.java`.
   * Observa cómo el servidor atiende ambos clientes en paralelo mostrando trazas independientes en la consola principal del servidor.

---

## 🤖 Declaración Transparente del Uso de Inteligencia Artificial

* **Herramienta asistencial utilizada:** Gemini (Google AI).
* **Propósito y alcance:** Durante la fase inicial de configuración del entorno en Windows, surgió un inconveniente técnico donde el comando `javac` no era reconocido por el sistema debido a la falta de configuración en la variable de entorno `PATH` del JDK[cita: 1]. Se consultó a la herramienta de IA para diagnosticar el problema, y se optó por la solución recomendada de compilar y ejecutar directamente mediante la extensión de Java para Visual Studio Code.