import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastSender {
    private static final String GROUP_ADDRESS = "239.255.255.250";
    private static final int GROUP_PORT = 5000;
    
    public static void main(String[] args) {
        MulticastSocket socket = null;
        
        try {
            // Crear socket multicast
            socket = new MulticastSocket();
            
            // Obtener dirección del grupo
            InetAddress groupAddress = InetAddress.getByName(GROUP_ADDRESS);
            
            // Obtener mensaje a enviar
            String message;
            if (args.length == 0) {
                message = "Mensaje por defecto desde emisor";
                System.out.println("No se proporcionó mensaje. Usando mensaje por defecto.");
            } else {
                // Concatenar todos los argumentos como mensaje
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    sb.append(args[i]);
                    if (i < args.length - 1) {
                        sb.append(" ");
                    }
                }
                message = sb.toString();
            }
            
            System.out.println("=== Emisor Multicast ===");
            System.out.println("Enviando mensaje al grupo: " + GROUP_ADDRESS + ":" + GROUP_PORT);
            System.out.println("Mensaje: " + message);
            
            // Convertir mensaje a bytes
            byte[] messageBytes = message.getBytes();
            
            // Crear paquete datagrama con destino al grupo multicast
            DatagramPacket packet = new DatagramPacket(
                messageBytes, 
                messageBytes.length, 
                groupAddress, 
                GROUP_PORT
            );
            
            // Enviar mensaje al grupo
            socket.send(packet);
            
            System.out.println("¡Mensaje enviado exitosamente a los 3 nodos del grupo!");
            
        } catch (UnknownHostException e) {
            System.err.println("Error: Dirección de grupo inválida - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S al enviar mensaje - " + e.getMessage());
        } finally {
            // Cerrar socket
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
