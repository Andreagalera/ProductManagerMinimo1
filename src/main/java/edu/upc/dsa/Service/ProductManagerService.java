package edu.upc.dsa.Service;

import edu.upc.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value="/orders", description = "Endpoint to Order Service")
@Path("/orders")
public class ProductManagerService {

    final static Logger log = Logger.getLogger(ProductManagerService.class.getName());

    private PedidoClass pedido1;

    private ProductManager pm;

    private List<LProductoClass> lp1;

    public ProductManagerService() throws UserNotFoundException {
        this.pm = ProductManagerImpl.getInstance();
        if (pm.size() == 0) {
            ProductoClass producto1 = new ProductoClass("Manzana", 1.5);
            ProductoClass producto2 = new ProductoClass("Pastel", 10);
            ProductoClass producto3 = new ProductoClass("Cerveza", 2.5);
            ProductoClass producto4 = new ProductoClass("Calamares", 5);
            pm.addProducto(producto1);
            pm.addProducto(producto2);
            pm.addProducto(producto3);
            pm.addProducto(producto4);
            pm.addUser("Pepe");
        }
    }

    @GET
    @ApiOperation(value = "get all products in the list", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ProductoClass.class, responseContainer = "List of Products")
    })
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        List<ProductoClass> productos = this.pm.allProducts();

        GenericEntity<List<ProductoClass>> entity = new GenericEntity<List<ProductoClass>>(productos) {
        };
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get all orders of a user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PedidoClass.class, responseContainer = "List of Orders"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders(@PathParam("user") String user) {
        List<PedidoClass> pedidos;
        try {
            pedidos = this.pm.damePedidosUsuario(user);
            log.info("Pedido: " + pedidos);
            GenericEntity<List<PedidoClass>> entity = new GenericEntity<List<PedidoClass>>(pedidos) {
            };
            return Response.status(201).entity(entity).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get all products sorted by price", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ProductoClass.class, responseContainer = "List of Products")
    })
    @Path("/sortedbyprice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsSortedByPrice() {
        List<ProductoClass> productos = this.pm.findAllProductsOrderedByPrice();

        GenericEntity<List<ProductoClass>> entity = new GenericEntity<List<ProductoClass>>(productos) {
        };
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get all products sorted by number of sales", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ProductoClass.class, responseContainer = "List of Products")
    })
    @Path("/sortedbysales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {
        List<ProductoClass> productos = this.pm.findAllProductsOrderedbyVentas();

        GenericEntity<List<ProductoClass>> entity = new GenericEntity<List<ProductoClass>>(productos) {
        };
        return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "place an Order", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/placeanorder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeAnOrder(PedidoClass p) throws UserNotFoundException {

        String userName = p.getUsuario().getNombre();

        try {
            this.pm.realizarPedido(userName, p);
            return Response.status(201).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @DELETE
    @ApiOperation(value = "serve an Order", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PedidoClass.class, responseContainer = "Pedido")
    })
    @Path("/serveanorder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response serveAnOrder() {
        PedidoClass pedido = this.pm.servirPedido();

        return Response.status(201).entity(pedido).build();
    }
}
