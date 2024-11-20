import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Stock inicial de productos
        HashMap<String, Integer> stock = new HashMap<>();
        stock.put("Pan", 10);
        stock.put("Leche", 15);
        stock.put("Huevos", 20);
        stock.put("Manzanas", 8);
        stock.put("Queso", 5);

        // Carrito de compras
        LinkedList<String> carrito = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 7) {
            // Menú de opciones
            System.out.println("""
                    \n--- Gestión de Productos con Stock ---
                    1. Ver lista de productos en el carrito
                    2. Ver stock disponible
                    3. Agregar producto al carrito
                    4. Eliminar producto del carrito
                    5. Calcular total de productos en el carrito
                    6. Reabastecer producto
                    7. Salir
                    Selecciona una opción: """);
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    // Ver productos en el carrito
                    System.out.println("\nProductos en el carrito:");
                    if (carrito.isEmpty()) {
                        System.out.println("El carrito está vacío.");
                    } else {
                        for (String prod : carrito) {
                            System.out.println(prod);
                        }
                    }
                    break;

                case 2:
                    // Ver stock disponible
                    System.out.println("\nStock disponible:");
                    for (String producto : stock.keySet()) {
                        System.out.println(producto + ": " + stock.get(producto) + " unidades");
                    }
                    break;

                case 3:
                    // Agregar producto al carrito
                    System.out.print("Ingresa el nombre del producto: ");
                    String productoAgregar = scanner.nextLine().trim();  // Usamos trim() para eliminar espacios extra

                    // Verifica si el nombre del producto ingresado está en stock sin importar mayúsculas o minúsculas
                    boolean productoEncontrado = false;
                    for (String key : stock.keySet()) {
                        if (key.equalsIgnoreCase(productoAgregar)) {
                            productoEncontrado = true;
                            break;
                        }
                    }

                    if (productoEncontrado) {
                        // Verificar si el producto tiene stock
                        Integer cantidadDisponible = stock.get(productoAgregar);  // Esto puede devolver null si no existe
                        if (cantidadDisponible != null && cantidadDisponible > 0) {
                            carrito.add(productoAgregar);
                            stock.put(productoAgregar, cantidadDisponible - 1); // Descontar del stock
                            System.out.println(productoAgregar + " agregado al carrito. Stock restante: " + (cantidadDisponible - 1));
                        } else {
                            System.out.println("Lo siento, " + productoAgregar + " no tiene stock disponible.");
                        }
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 4:
                    // Eliminar producto del carrito
                    System.out.print("Ingresa el nombre del producto a eliminar: ");
                    String productoEliminar = scanner.nextLine().trim();

                    if (carrito.contains(productoEliminar)) {
                        carrito.remove(productoEliminar);
                        Integer cantidadDisponible = stock.get(productoEliminar);
                        if (cantidadDisponible != null) {
                            stock.put(productoEliminar, cantidadDisponible + 1); // Reponer el stock
                            System.out.println(productoEliminar + " eliminado del carrito. Stock reabastecido.");
                        }
                    } else {
                        System.out.println("Producto no encontrado en el carrito.");
                    }
                    break;

                case 5:
                    // Calcular total de productos en el carrito
                    System.out.println("Total de productos en el carrito: " + carrito.size());
                    break;

                case 6:
                    // Reabastecer producto
                    System.out.print("Ingresa el nombre del producto a reabastecer: ");
                    String productoReabastecer = scanner.nextLine().trim();
                    System.out.print("Ingresa la cantidad a reabastecer: ");
                    int cantidadReabastecer = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea

                    if (stock.containsKey(productoReabastecer)) {
                        Integer cantidadActual = stock.get(productoReabastecer);
                        stock.put(productoReabastecer, cantidadActual + cantidadReabastecer);
                        System.out.println("Producto " + productoReabastecer + " reabastecido. Stock actual: " + (cantidadActual + cantidadReabastecer));
                    } else {
                        System.out.println("Producto no encontrado en el inventario.");
                    }
                    break;

                case 7:
                    // Salir del programa
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    // Manejo de opción no válida
                    System.out.println("Opción no válida. Por favor, selecciona una opción entre 1 y 7.");
            }
        }

        scanner.close();
    }
}
