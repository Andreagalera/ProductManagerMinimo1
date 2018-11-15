package edu.upc.dsa;

public class LProductoClass {
    //Atributos
    public int cantidad;
    public String producto;

    @Override
    public String toString() {
        return "Pedido [Nombre:" + producto + ", Cantidad:" + cantidad + "]";
    }
}
