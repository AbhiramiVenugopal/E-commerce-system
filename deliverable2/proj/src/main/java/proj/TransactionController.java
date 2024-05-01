package proj;

import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("/payment")
public class TransactionController {

	private TransactionDAO tranDAO = new TransactionDAO();
	private ItemDAO itemDAO = new ItemDAO();
	// private UserDAO userDAO = new UserDAO();

	@POST
	@Path("/submit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction createTransaction(Transaction info, @QueryParam("itemID") int itemId, @QueryParam("userID") int userId,
			@QueryParam("isExpe") boolean isExpe) throws SQLException {

		Item item = itemDAO.read(itemId);

		double shippingPrice = item.getShippingPrice();
		double itemPrice = item.getPrice();
		double expePrice = 0;
		if (isExpe) {
			expePrice = item.getExpeditedCost();
		}
		double total = shippingPrice + itemPrice + expePrice;

		info.setTotalCost(total);
		info.setItemID(itemId);
		info.setUserID(userId);

		return tranDAO.create(info);
	}

	@GET
	@Path("/order")
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction getTransaction(@QueryParam("id") int id, @QueryParam("item") int itemId,
			@QueryParam("user") int userId) {
		return tranDAO.read(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction updateTransaction(@PathParam("id") int id, Transaction card) {
		return tranDAO.update(id, card);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteTransaction(@PathParam("id") int id) {
		tranDAO.delete(id);
	}

}
