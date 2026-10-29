# Ejercicio b - Comunicación Multicast en Grupo

Se requiere armar un grupo de máquinas (3 nodos), asignar direcciones de grupo a cada nodo y enviar un mensaje al grupo. El mensaje puede ser un número entero o una cadena de caracteres.

## Objetivo
Crear un grupo multicast con 3 nodos, asignar direcciones IP multicast a cada uno y realizar el envío de un mensaje al grupo.

## Configuración

### Dirección de grupo multicast
Se utilizará una dirección IP de clase D dentro del rango 224.0.0.0 - 239.255.255.255. 
Para este ejercicio se propone:
- Dirección de grupo: `239.255.255.250` (o cualquier dirección válida del rango 239.0.0.0/8 para uso administrativo/privado)
- Puerto: `5000`

### Nodos del grupo
Se conformará un grupo con 3 máquinas/nodos:
- Nodo 1 (Host A)
- Nodo 2 (Host B)  
- Nodo 3 (Host C)

Cada nodo se suscribe a la misma dirección multicast para pertenecer al grupo.

## Implementación

### Archivos incluidos:
- `MulticastSender.java` - Emisor que envía mensaje al grupo
- `MulticastReceiver.java` - Receptor que escucha mensajes del grupo
- `config.properties` - Configuración de grupo (dirección y puerto)
- `ejecutar_grupo.sh` / `ejecutar_grupo.bat` - Scripts para ejecutar los 3 nodos

## Instrucciones de uso

### Compilar
```bash
javac MulticastSender.java MulticastReceiver.java
```

### Ejecutar los 3 nodos (en terminales separadas)
```bash
# Terminal 1 - Nodo 1
java MulticastReceiver

# Terminal 2 - Nodo 2  
java MulticastReceiver

# Terminal 3 - Nodo 3
java MulticastReceiver
```

### Enviar mensaje al grupo
```bash
java MulticastSender "Hola grupo"
# o
java MulticastSender 12345
```

Todos los nodos suscritos al grupo recibirán el mensaje simultáneamente.

## Notas
- Se utiliza UDP con MulticastSocket (Java)
- Los nodos deben estar en la misma red local o con routing multicast habilitado
- Se requiere el protocolo IGMP habilitado en la red
