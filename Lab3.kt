import kotlin.system.measureTimeMillis
// Programa interactivo para practicar algoritmos de ordenamiento y recursividad
fun main() {
    while (true) {
        // Mostrar el menú principal
        println("\n[*** MENÚ PRINCIPAL ***]")
        println("1. Ordenar con Bubble Sort")
        println("2. Ordenar con Quick Sort")
        println("3. Calcular el factorial de un número")
        println("4. Resolver las Torres de Hanói")
        println("5. Salir")
        print("Seleccione una opción: ")

        // Leer la opción del usuario y ejecutar la acción correspondiente
        when (readLine()?.toIntOrNull()) {
            1 -> ejecutarBubbleSort()
            2 -> ejecutarQuickSort()
            3 -> ejecutarFactorial()
            4 -> ejecutarTorresDeHanoi()
            5 -> {
                println("Saliendo del programa... ¡Hasta luego!")
                break
            }
            else -> println("Opción inválida. Intente de nuevo.")
        }
    }
}

// Función para ejecutar el algoritmo Bubble Sort
fun ejecutarBubbleSort() {
    println("\n--- BUBBLE SORT ---")
    println("Ingrese números separados por comas (ejemplo: 8,3,5,1):")
    // Leer y convertir la entrada en una lista de números enteros
    val numeros = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() }

    if (numeros == null || numeros.isEmpty()) {
        println("Entrada no válida.") // Validar entrada
        return
    }

    println("Lista original: $numeros")
    // Medir el tiempo de ejecución del algoritmo
    val tiempo = measureTimeMillis {
        val ordenada = bubbleSortOptimizado(numeros.toMutableList())
        println("Lista ordenada: $ordenada") // Mostrar la lista ordenada
    }
    println("Tiempo de ejecución: ${tiempo}ms") // Mostrar tiempo de ejecución
}

// Implementación del algoritmo Bubble Sort con optimización
fun bubbleSortOptimizado(lista: MutableList<Int>): List<Int> {
    for (i in 0 until lista.size - 1) {
        var swapped = false // Variable para verificar si hubo intercambios
        for (j in 0 until lista.size - 1 - i) {
            if (lista[j] > lista[j + 1]) {
                // Intercambiar elementos si están en el orden incorrecto
                lista[j] = lista[j + 1].also { lista[j + 1] = lista[j] }
                swapped = true
            }
        }
        if (!swapped) break // Detener si no hubo intercambios
    }
    return lista
}

// Función para ejecutar el algoritmo Quick Sort
fun ejecutarQuickSort() {
    println("\n--- QUICK SORT ---")
    println("Ingrese números separados por comas (ejemplo: 8,3,5,1):")
    val numeros = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() }

    if (numeros == null || numeros.isEmpty()) {
        println("Entrada no válida.") // Validar entrada
        return
    }

    println("Lista original: $numeros")
    // Medir el tiempo de ejecución del algoritmo
    val tiempo = measureTimeMillis {
        val ordenada = quickSort(numeros)
        println("Lista ordenada: $ordenada") // Mostrar la lista ordenada
    }
    println("Tiempo de ejecución: ${tiempo}ms") // Mostrar tiempo de ejecución
}

// Implementación del algoritmo Quick Sort
fun quickSort(lista: List<Int>): List<Int> {
    if (lista.size <= 1) return lista // Caso base: lista con un solo elemento
    val pivote = lista.last() // Seleccionar el último elemento como pivote
    val menores = lista.filter { it < pivote } // Elementos menores que el pivote
    val iguales = lista.filter { it == pivote } // Elementos iguales al pivote
    val mayores = lista.filter { it > pivote } // Elementos mayores que el pivote
    // Ordenar recursivamente y combinar las sublistas
    return quickSort(menores) + iguales + quickSort(mayores)
}

// Función para calcular el factorial
fun ejecutarFactorial() {
    println("\n--- FACTORIAL ---")
    print("Ingrese un número entero positivo: ")
    val numero = readLine()?.toIntOrNull()

    if (numero == null || numero < 0) {
        println("Por favor, ingrese un número válido.") // Validar entrada
        return
    }

    val resultado = factorial(numero) // Calcular el factorial
    println("El factorial de $numero es $resultado") // Mostrar el resultado
}

// Implementación recursiva para calcular el factorial
fun factorial(n: Int): Long {
    return if (n == 0) 1 else n * factorial(n - 1) // Caso base y recursión
}

// Función para resolver las Torres de Hanói
fun ejecutarTorresDeHanoi() {
    println("\n--- TORRES DE HANÓI ---")
    print("Ingrese el número de discos: ")
    val discos = readLine()?.toIntOrNull()

    if (discos == null || discos <= 0) {
        println("Por favor, ingrese un número válido.") // Validar entrada
        return
    }

    println("Resolviendo Torres de Hanói con $discos discos:")
    // Llamar a la función recursiva para resolver el problema
    hanoi(discos, "A", "C", "B")
}

// Implementación recursiva para resolver las Torres de Hanói
fun hanoi(n: Int, origen: String, destino: String, auxiliar: String) {
    if (n == 1) {
        println("Mover disco 1 de $origen a $destino") // Caso base
    } else {
        hanoi(n - 1, origen, auxiliar, destino) // Mover n-1 discos al auxiliar
        println("Mover disco $n de $origen a $destino") // Mover disco más grande
        hanoi(n - 1, auxiliar, destino, origen) // Mover n-1 discos al destino
    }
}
