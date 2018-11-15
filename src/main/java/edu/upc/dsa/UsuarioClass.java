package edu.upc.dsa;

import java.util.LinkedList;

public class UsuarioClass {

    //Atributos
    String nombre;
    LinkedList<PedidoClass> pedidos;

    //Constructor json
    public UsuarioClass(){}
    //Constructor solo pasamos ususarios ya que la lista esta vacia
    public UsuarioClass(String nombre){
        this.nombre=nombre;
        this.pedidos= new LinkedList<>();
    }

    //Gets i Sets
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public LinkedList<PedidoClass> getPedidos() {
        return pedidos;
    }

    public void setPedidos(LinkedList<PedidoClass> pedidos) {
        this.pedidos = pedidos;
    }

    //Añadimos addPedido en Usuario para saberlo para el servirPedido
    public void addPedido(PedidoClass pedido){
        this.pedidos.add(pedido);
    }

    @Override
    public String toString() {
        String s="";
        for(int i=0; i < this.pedidos.size();i++)
        s= "Producto [Name = " + pedidos.get(0).productos.get(i).producto + ", Cantidad = " + pedidos.get(0).productos.get(i).cantidad + "]";
        return s;
    }

}
