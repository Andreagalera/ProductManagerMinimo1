package edu.upc.dsa;

public class ProductoClass {

    //Atributes
    private int ventas=0;
    double precio;
    String nombre;

    //Constructor
    public ProductoClass(String nombre, double precio){
        this.nombre=nombre;
        this.precio=precio;
    }
    public ProductoClass(){}

    //Gets i Sets
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getVentas() {
        return ventas;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Modificar ventas no set
    public void addSales (int cantidad) {
        this.ventas=this.ventas +cantidad;
    }
}
