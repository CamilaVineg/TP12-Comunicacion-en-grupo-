#!/bin/bash
echo "========================================"
echo "  EJERCICIO B - GRUPO MULTICAST (3 NODOS)"
echo "========================================"
echo ""
echo "Compilando archivos Java..."
javac MulticastSender.java MulticastReceiver.java
if [ $? -ne 0 ]; then
    echo "Error al compilar. Verifique el código."
    exit 1
fi
echo "Compilación exitosa."
echo ""
echo "Para ejecutar el grupo de 3 nodos:"
echo "  1. Abra 3 terminales diferentes"
echo "  2. En cada terminal ejecute: java MulticastReceiver"
echo "  3. En una 4ta terminal ejecute: java MulticastSender \"su mensaje\""
echo ""
echo "Abriendo 3 terminales con receptores..."
gnome-terminal --title="Nodo 1 - Receptor" -- bash -c "java MulticastReceiver; exec bash" &
gnome-terminal --title="Nodo 2 - Receptor" -- bash -c "java MulticastReceiver; exec bash" &
gnome-terminal --title="Nodo 3 - Receptor" -- bash -c "java MulticastReceiver; exec bash" &
echo ""
echo "Se han abierto 3 terminales con los receptores."
echo "Ahora puede enviar un mensaje desde otra terminal con:"
echo "  java MulticastSender \"Hola grupo de 3 nodos\""
