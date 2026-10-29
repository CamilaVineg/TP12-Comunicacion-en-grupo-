import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastReceiver {
    private static final String GROUP_ADDRESS = "239.255.255.250";
    private static final int GROUP_PORT = 5000;
    private static final int BUFFER_SIZE = 1024;
    
    public static void main(String[] args) {
        MulticastSocket socket = null;
        InetAddress groupAddress = null;
        
        try {
            // Crear socket multicast asociado al puerto
            socket = new MulticastSocket(GROUP_PORT);
            groupAddress = InetAddress.getByName(GROUP_ADDRESS);
            
            // Unirse al grupo multicast
            socket.joinGroup(groupAddress);
            System.out.println("=== Nodo Receptor Multicast ===");
            System.out.println("Unido al grupo: " + GROUP_ADDRESS + ":" + GROUP_PORT);
            System.out.println("Esperando mensajes del grupo...");
            System.out.println("Presione Ctrl+C para salir\n");
            
            // Buffer para recibir datos
            byte[] buffer = new byte[BUFFER_SIZE];
            
            while (true) {
                // Crear paquete para recibir
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                // Recibir mensaje (bloqueante)
                socket.receive(packet);
                
                // Extraer mensaje
                String message = new String(packet.getData(), 0, packet.getLength());
                InetAddress senderAddress = packet.getAddress();
                
                // Mostrar mensaje recibido
                System.out.println("[" + new java.util.Date() + "]");
                System.out.println("Mensaje recibido desde: " + senderAddress.getHostAddress());
                System.out.println("Contenido: " + message);
                System.out.println("-----------------------------------\n");
            }
            
        } catch (UnknownHostException e) {
            System.err.println("Error: Dirección de grupo inválida - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } finally {
            // Abandonar grupo y cerrar socket
            if (socket != null && groupAddress != null && !socket.isClosed()) {
                try {
                    socket.leaveGroup(groupAddress);
                    socket.close();
                    System.out.println("Nodo desconectado del grupo multicast.");
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket: " + e.getMessage());
                }
            }
        }
    }
}
