package proj;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/items")
public class ItemController {
	
	@Context
	private HttpServletRequest servletRequest;

    private ItemDAO itemDAO = new ItemDAO();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        List<Item> items = itemDAO.readAll();
        return Response.ok(items).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getItemById(@PathParam("id") int id) {
        Item item = itemDAO.read(id);
        if (item != null) {
            return Response.ok(item).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Item not found").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("search/{keyword}")
    public Response getItemsByKeyword(@PathParam("keyword") String keyword) {
        List<Item> items = itemDAO.readByKeyword(keyword);
        return Response.ok(items).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(Item item) {
    	HttpSession session = servletRequest.getSession();
    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	item.setSeller(loggedInUser.getUsername());
    	
        if (itemDAO.create(item)) {
            return Response.status(Response.Status.CREATED).entity(item).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Item creation failed").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id") int id) {
        if (itemDAO.delete(id)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Item not found or deletion failed").build();
        }
    }	

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSearchPage(@QueryParam("keyword") String keyword) {
        
        List<Item> items;
        if (keyword == null || keyword.isEmpty()) {
            items = itemDAO.readAll();
        } else {
            items = itemDAO.readByKeyword(keyword);
        }
        
        return Response.status(Response.Status.OK).entity(items).build();
    }
    
}
