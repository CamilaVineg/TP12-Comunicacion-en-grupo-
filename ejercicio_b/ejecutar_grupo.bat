@echo off
echo ========================================
echo   EJERCICIO B - GRUPO MULTICAST (3 NODOS)
echo ========================================
echo.
echo Compilando archivos Java...
javac MulticastSender.java MulticastReceiver.java
if %errorlevel% neq 0 (
    echo Error al compilar. Verifique el código.
    pause
    exit /b 1
)
echo Compilación exitosa.
echo.
echo Para ejecutar el grupo de 3 nodos:
echo   1. Abra 3 terminales diferentes
echo   2. En cada terminal ejecute: java MulticastReceiver
echo   3. En una 4ta terminal ejecute: java MulticastSender "su mensaje"
echo.
echo Presione cualquier tecla para abrir 3 terminales con receptores...
pause
start "Nodo 1 - Receptor" cmd /k "java MulticastReceiver"
start "Nodo 2 - Receptor" cmd /k "java MulticastReceiver"
start "Nodo 3 - Receptor" cmd /k "java MulticastReceiver"
echo.
echo Se han abierto 3 terminales con los receptores.
echo Ahora puede enviar un mensaje desde otra terminal con:
echo   java MulticastSender "Hola grupo de 3 nodos"
echo.
pause
