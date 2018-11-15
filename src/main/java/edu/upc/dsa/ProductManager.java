package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

public interface ProductManager {

    //Metodos
    void realizarPedido(String u, PedidoClass p) throws UserNotFoundException;
    //Servir un pedido, quitarlo de la cola.Que es?
    PedidoClass servirPedido();
    //Productos ordenados por precio ascendente
    List<ProductoClass> findAllProductsOrderedByPrice();
    //Producto ordenados por venta descendente
    List<ProductoClass> findAllProductsOrderedbyVentas();
    //Linked lista linkedlist Cua
    LinkedList<PedidoClass> damePedidosUsuario(String usuario) throws UserNotFoundException;

    void addUser(String u);
    void addProducto(ProductoClass p);
    List<ProductoClass> allProducts();
    int size();
}
