package edu.upc.dsa;

import java.util.*;
import java.util.logging.Logger;

public class ProductManagerImpl implements ProductManager {

    //LLamamos a las propiedades log4j del archivo
    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());

    //Singleton
    //Inicializamos producto
    private static ProductManager instance;

    //Metodo que retorna ejemplo tipo instance
    //Si instance ea nulo genera instancia
    public static ProductManager getInstance() {
        if (instance == null) {
            //Generamos una instancia
            instance = new ProductManagerImpl();
        }
        return instance;
    }
    //Constructor privado
    private ProductManagerImpl() {
        productos = new ArrayList<>();
        pedidos = new LinkedList<PedidoClass>();
        usuarios = new HashMap<>();
    }

    //Declaramos listas
    //Lista que va en peidos
    private List<ProductoClass> productos;
    //LinkedList que va en Pedido
    private LinkedList<PedidoClass> pedidos;
    //Inicializamos el hashmap(key: string; value: Usuarios) de UsuarioClass
    private HashMap<String, UsuarioClass> usuarios;

    //Obtener tamaño lista productos
    public int size(){
        log.info("Tamaño" + this.productos.size());
        return this.productos.size();
    }

    //Añadir ususario
    public void addUser(String u){
        log.info("Usuario para añadir "  +u);
        usuarios.put(u, new UsuarioClass((u)));
    }

    //Añadir producto
    public void addProducto(ProductoClass p){
        log.info("Producto añadido: " +p);
        this.productos.add(p);
    }

    //Listar todos los productos
    public List<ProductoClass> allProducts(){
        List<ProductoClass> ret =new ArrayList<>();
        ret.addAll(this.productos);
        return ret;
    }

   //Realizar pedido
    public void realizarPedido(String u, PedidoClass p) throws UserNotFoundException {
        //Buscr el usuario
        UsuarioClass theUser =this.usuarios.get(u);
        log.info("Usuario dado: " +theUser);

        //Miramos si ya esta
        if(theUser != null){
            //Asignamos el pedido con el ususario
           p.setUsuario(theUser);
           this.pedidos.add(p);
           log.info("Pedido hecho: " +this.pedidos);
        }
        //Si es nuevo el usuario
        else{
            //log.error("UserNotFound");
            throw new UserNotFoundException();
        }
    }

    //Dar info producto respecto a nombre
    private ProductoClass getProducto(String producto){
        log.info("Producto pasado: " +producto);
        //log.degug("I'm in");
        ProductoClass p =null;
        for(int i =0; i<this.productos.size();i++){
            if(producto.equals(this.productos.get(i).nombre)){
                p=this.productos.get(i);
            }
        }
        log.info("Información de los nombres y cantidad de cada producto: " +p);
        return p;
    }

    //Metodo para incrementar ventas
    private void process(PedidoClass p){
        log.info("2Entramos al proceso");
    //Guardamos el pedido en producto para guardar las ventas de cada prdocuto y incrementarla
        List<LProductoClass> ListaProducto =p.getProductos();
        log.info("Ver lista de productos del un pedido: " + ListaProducto);
        //Creamos producto para guardar los productos
        ProductoClass producto;

        //Para cada pedido incrementamos las vendas
        //Recorremos cada pedido producto a producto
        for(LProductoClass lp:ListaProducto){
            log.info("Nombre pedido: " + lp.producto);
            log.info("Cantidad pedido: " + lp.cantidad);
            producto=this.getProducto(lp.producto);

            producto.addSales(lp.cantidad);
            log.info("Ventas producto antes de incremnetar: " +producto.getVentas());
        }

    }

    public PedidoClass servirPedido(){
        //Eliminamos el primero de la linkedlist
        PedidoClass p=this.pedidos.remove();
        log.info("Ver el pedido: " + p);

        log.info("1Intentamos entrar al proces");
        //Tenemos que incrementar la venta
        process(p);
        log.info("3Volvemos a entrar a la funcion servir pedido");

        //Añadimos el pedido a la lista de pedidos del usuario
        UsuarioClass u =p.getUsuario();
        log.info("Usuario: " + u);
        u.addPedido(p);

        return p;
    }
   //Retornar productos ordenados por precio ASCENDIENTE
    public List<ProductoClass> findAllProductsOrderedByPrice(){
        log.info("Lista de productos antes de ordenar por precio: " + this.productos);
        //We want to create a copy of the actual list of Employees, because it will be better not to modify the list.
        List<ProductoClass> ret = new ArrayList<>();
        ret.addAll(this.productos);

        //As we did in figures, we have to tell to the sort method, which criteria has to apply
        Collections.sort(ret, new Comparator<ProductoClass>() {
            @Override
            //Function to compare the salary
            public int compare(ProductoClass pr1, ProductoClass pr2) {
                return (int)(pr1.getPrecio()-pr2.getPrecio());
            }
        });
        log.info("List despues de cambios de precio ASC: " + ret);

        return ret;
    }
    //Retornar productos ordenados por ventas DESCENDIENTE
    public List<ProductoClass> findAllProductsOrderedbyVentas(){
        log.info("Lista antes de ordenar por ventas: "+ this.productos);
        //We want to create a copy of the actual list of Employees, because it will be better not to modify the list.
        // List<ProductClass> ret = new LinkedList<ProductClass>();
        List<ProductoClass> ret = new ArrayList<>();
        ret.addAll(this.productos);

        //As we did in figures, we have to tell to the sort method, which criteria has to apply
        Collections.sort(ret, new Comparator<ProductoClass>() {
            @Override
            //Function to compare the salary
            public int compare(ProductoClass pr1, ProductoClass pr2) {
                return (-1)*(pr1.getVentas()-pr2.getVentas());
            }
        });
        log.info("Lista ordenada por ventas DESC: " + ret);
        return ret;
    }
    //Retorna pedidos de un usuario
    public LinkedList<PedidoClass> damePedidosUsuario(String usuario) throws UserNotFoundException{
        LinkedList<PedidoClass> pedidos;

        //We want theCompany value, given a company entered by the user
        UsuarioClass theUser = this.usuarios.get(usuario);
        log.info("Usuario de dame pedidos de usuario: " + theUser);
        if (theUser!=null) {
            pedidos = theUser.getPedidos();
        }
        else {
            //log.error("UserNotFound");
            throw new UserNotFoundException();
        }

        log.info("Pedidos de un usuario: " + this.pedidos);
        return pedidos;
    }
}
