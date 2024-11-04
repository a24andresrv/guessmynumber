import java.io.File
const val BG_GREEN = "\u001B[42m"
const val BG_YELLOW = "\u001B[43m"
const val GREEN = "\u001B[32m"
const val RESET = "\u001B[0m"
const val BLACK = "\u001B[30m"
const val BOLD = "\u001B[1m"
fun numerosecreto():String{
    val lista= mutableListOf(1,2,3,4,5,6)
    lista.shuffle()
    var numerosecreto = ""
    for (i in 0 until 4){
        numerosecreto = numerosecreto + lista[i]
    }
    return numerosecreto
}
fun jugar():Unit {
    val secreto = numerosecreto()
    File("traza.txt").writeText("numero secreto: $secreto\n")
    println(secreto)
    var intentos = 2
    var aciertos = 0
    var coincidencias = 0
    val lectura=""
    for (i in 1 .. intentos) {
        print("${BOLD}Teclea un número de 4 cifras sin números repetidos: ")
        val lectura = readln()
        aciertos = 0
        coincidencias = 0
        for (i in 0..secreto.lastIndex) {
            if (secreto[i] == lectura[i]) {
                aciertos++
            } else {
                for (j in 0..lectura.lastIndex) {
                    if (secreto[i] == lectura[j]) {
                        coincidencias++
                    }
                }
            }
        }
        if (aciertos == secreto.length) {
            println("Has adivinado el número secreto")
            File("traza.txt").appendText("Intento $i: $lectura, Has adivinado el número secreto.\n")
            break
        }
        print(lectura+"   ")
        print("${BG_GREEN}${BLACK}  $aciertos  ")
        print("${BG_YELLOW}${BLACK}  $coincidencias  ${RESET}")
        println()
        File("traza.txt").appendText("Intento $i: $lectura, Aciertos: $aciertos, Coincidencias: $coincidencias\n")
    }
    if(aciertos != secreto.length){
        println("Lo siento no adivinaste el número secreto $secreto en $intentos intentos")
    }

}
fun traza(){
    var contenido=File("traza.txt").readLines()
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