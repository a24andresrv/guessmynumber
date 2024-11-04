import java.io.File
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
    println(secreto)
    var intentos = 2
    var aciertos = 0
    var coincidencias = 0
    for (intentos in 0 until intentos) {
        print("teclea un número de 4 cifras sin números repetidos: ")
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
            break
        }
        println("aciertos = $aciertos")
        println("coincidencias = $coincidencias")
    }
    if(aciertos != secreto.length){
        println("Lo siento no adivinaste el número secreto $secreto en $intentos intentos")
    }
    File("traza.txt")
    println()
}
fun traza(){

}

fun main() {
    var opcion = 0
    while (opcion != 3) {
        println("1. Jugar")
        println("2. Ver traza de último intento")
        println("3. Salir")
        print("opción: ")
        opcion= readln().toInt()
        when (opcion) {
            1 -> jugar()
            2 -> traza()
        }
    }
    println("Gracias por jugar.")
}