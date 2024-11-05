import java.io.File
const val BG_GREEN = "\u001B[42m"
const val BG_YELLOW = "\u001B[43m"
const val GREEN = "\u001B[32m"
const val RESET = "\u001B[0m"
const val BLACK = "\u001B[30m"
const val BOLD = "\u001B[1m"
//Función para crear un número aleatorio
fun numerosecreto():String{
    val lista= mutableListOf(1,2,3,4,5,6)
    lista.shuffle()
    var numerosecreto = ""
    for (i in 0 until 4){
        numerosecreto = numerosecreto + lista[i]
    }
    return numerosecreto
}
//Función que se encarga del juego
fun jugar() {
    val secreto = numerosecreto()
    File("traza.txt").writeText("numero secreto: $secreto\n")
    //println(secreto)
    //Utilicé una variable para faciliatra la modificación de la cantidad de intetnos sin tener que modificar el bucle
    //Podría preguntarse al jugadro cunanto intentos quiere y almacenarse en la variable
    val intentos = 8
    print("Tienes $intentos para adivinar el número secreto.")
    var aciertos = 0
    var coincidencias = 0
    for (i in 1 .. intentos) {
        print("Teclea un número de 4 cifras sin números repetidos: ")
        val lectura = readln()
        //ponemos a cero las variables por cada intento
        aciertos = 0
        coincidencias = 0
        for (j in 0..secreto.lastIndex) {
            //si la posición de la cifra es la misma en el numero secreto que en el numero introducido sumar 1 acierto
            if (secreto[j] == lectura[j]) {
                aciertos++
            } else {
                //Si no, recorre el numero introducido en busca de coincidecnias, esta solución es validad al no haber repeticiones de cifras.
                for (c in 0..lectura.lastIndex) {
                    if (secreto[j] == lectura[c]) {
                        coincidencias++
                    }
                }
            }
        }
        //Si adivina el numero secreto sale del bucle
        if (aciertos == secreto.length) {
            println("Has adivinado el número secreto")
            File("traza.txt").appendText("Intento $i: $lectura, Has adivinado el número secreto.\n")
            break
        }
        //Muestr al umero introducido y los aciertos y coincidencias
        print(lectura+"   ")
        print("${BG_GREEN}${BLACK}  $aciertos  ")
        print("${BG_YELLOW}${BLACK}  $coincidencias  ${RESET}")
        println()
        File("traza.txt").appendText("Intento $i: $lectura, Aciertos: $aciertos, Coincidencias: $coincidencias\n")
    }
    //Si al terminar los intentos no se acierta el número, mostrar este mensaje
    if(aciertos != secreto.length){
        println("Lo siento no adivinaste el número secreto $secreto en $intentos intentos")
    }

}
//Función que obtiene los datos escritos en el documento
fun traza(){
    val contenido=File("traza.txt").readLines()
    for (linea in contenido){
        println(linea)
    }

}
fun main() {
    var opcion = 0
    while (opcion != 3) {
        println("${BOLD}${GREEN}1. Jugar")
        println("${GREEN}2. Ver traza de último intento")
        println("${GREEN}3. Salir")
        print("${GREEN}opción:${RESET} ")
        opcion= readln().toInt()
        when (opcion) {
            1 -> jugar()
            2 -> traza()
        }
    }
    println("${GREEN}Gracias por jugar.")
}