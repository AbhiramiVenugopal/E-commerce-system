package proj;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/bid")
public class AuctionController {

	@Context
	private HttpServletRequest servletRequest;
	 private ItemDAO itemDAO = new ItemDAO();
	 
	 
	 @GET
	 @Path("/forward")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getForwardPage( @QueryParam("itemID") int id) {
		 Item item = itemDAO.read(id);
		 return Response.status(Response.Status.OK).entity(item).build();
		 
	 }
	 
	 	@POST
	    @Path("/placeForwardBid")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response placeBid(BidRequest bidRequest) {
	        // Ensure the user is logged in
		 	HttpSession session = servletRequest.getSession();
		 	User loggedInUser = (User) session.getAttribute("loggedInUser");
		 	
	        if (loggedInUser == null) {
	            return Response.status(Response.Status.UNAUTHORIZED).entity("User not logged in").build();
	        }

	        // Retrieve the item for which the bid is being placed
	        int itemId = bidRequest.getItemId();
	        Item item = itemDAO.read(itemId);

	        if (item == null) {
	            return Response.status(Response.Status.NOT_FOUND).entity("Item not found").build();
	        }

	        // Validate the bid amount ( can add more validation)
	        double bidAmount = bidRequest.getBidAmount();
	        if (bidAmount <= item.getHighestBid()) {
	            return Response.status(Response.Status.BAD_REQUEST).entity("Bid must be higher than the current highest bid").build();
	        }

	        // Update the item's highest bid and bidder in the DB
	        bidRequest.setUserName(loggedInUser.getUsername());
		     itemDAO.updateBid(itemId, bidAmount, loggedInUser.getUsername());

		     // Notify connected WebSocket clients about the new bid
//		     BiddingWebSocket.broadcastBidUpdate(bidRequest);

		     return Response.status(Response.Status.OK).entity(bidRequest).build();
	    }
	 	
	 	
	 	 @GET
		 @Path("/dutch")
		 @Produces(MediaType.APPLICATION_JSON)
		 public Response getDutchPage( @QueryParam("itemID") int id) {
			 Item item = itemDAO.read(id);
			 return Response.status(Response.Status.OK).entity(item).build();
			 
	 	 }
	 	 
	 	 
	 	@POST
	    @Path("/placeDutchBid")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response placeDutchBid(BidRequest bidRequest) {
	        // Ensure the user is logged in
		 	HttpSession session = servletRequest.getSession();
		 	User loggedInUser = (User) session.getAttribute("loggedInUser");
		 	
	        if (loggedInUser == null) {
	            return Response.status(Response.Status.UNAUTHORIZED).entity("User not logged in").build();
	        }

	        // Retrieve the item for which the bid is being placed
	        int itemId = bidRequest.getItemId();
	        Item item = itemDAO.read(itemId);

	        if (item == null) {
	            return Response.status(Response.Status.NOT_FOUND).entity("Item not found").build();
	        }

	      

	       
	        bidRequest.setUserName(loggedInUser.getUsername());
//		     itemDAO.delete(itemId);

		     ForwardBidWebSocket.broadcastBidUpdate(bidRequest);

		     return Response.status(Response.Status.OK).entity(bidRequest).build();
	    }
	 	
	 	@GET
	 	@Path("/getLatestBid")
	 	@Produces(MediaType.APPLICATION_JSON)
	 	public Response getLatestBid(@QueryParam("itemID") int id) {
	 	    Item item = itemDAO.read(id);
	 	    if (item != null) {
	 	        BidRequest bid = new BidRequest();
	 	        bid.setBidAmount(Math.max(item.getHighestBid(), item.getPrice()));
	 	        bid.setUserName(item.getHighestBidder());
	 	        return Response.status(Response.Status.OK).entity(bid).build();
	 	    } else {
	 	        return Response.status(Response.Status.NOT_FOUND).entity("Item not found").build();
	 	    }
	 	}
	 	
	 	
	 	@GET
	 	@Path("/auctionEnded/{itemID}")
	 	@Produces(MediaType.APPLICATION_JSON)
	 	public Response paymentEnded(@PathParam("itemID") int itemID) {
	 		Item item = itemDAO.read(itemID);
	 		HttpSession session = servletRequest.getSession();
	 		User loggedInUser = (User) session.getAttribute("loggedInUser");
	 		if (loggedInUser == null) {
	            return Response.status(Response.Status.UNAUTHORIZED).entity("User not logged in").build();
	        }
	 		if (item == null) {
	 			 return Response.status(Response.Status.NOT_FOUND).entity("Item not found").build();
	 		}
	 		if (!loggedInUser.getUsername().equals(item.getHighestBidder())){
	 			return Response.status(Response.Status.UNAUTHORIZED).entity("User is not the winning bidder").build();
	 		}
	 		if (item != null) {
	 			//itemDAO.delete(itemID);
	 			 return Response.status(Response.Status.OK).entity(item).build();
	 		}else return Response.status(Response.Status.NOT_FOUND).entity("Item not found").build();
	 	}
	
	
}
