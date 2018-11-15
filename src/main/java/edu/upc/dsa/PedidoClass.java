package edu.upc.dsa;

import java.util.ArrayList;
import java.util.List;

public class PedidoClass {

    //Atributes
    private UsuarioClass usuario;
    //Hay que declarar lista pedido
   List<LProductoClass> productos;

   //Constructor vacio json
    public PedidoClass(){
        this.productos= new ArrayList<>();
    }
    //Constructor solo lis productos
   public PedidoClass(List<LProductoClass> productos){
       this.productos= productos;

   }

    public void setUsuario(UsuarioClass usuario) {
        this.usuario = usuario;
    }

    public UsuarioClass getUsuario() {
        return usuario;
    }

    public void setProductos(List<LProductoClass> productos) {
        this.productos = productos;
    }

    public List<LProductoClass> getProductos() {
        return productos;
    }
}


