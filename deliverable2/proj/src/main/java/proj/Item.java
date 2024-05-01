package proj;

public class Item {
	
    private int itemID;
    private String name;
    private String description;
    private String auctionType;
    private double price;
    private Double highestBid; // Nullable, hence using Double object
    private String seller;
    private String highestBidder;
    private String auctionEndTime;
    private int shippingTime;
    private double shippingPrice;
    private double expeditedCost;
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuctionType() {
		return auctionType;
	}
	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Double getHighestBid() {
		return highestBid;
	}
	public void setHighestBid(Double highestBid) {
		this.highestBid = highestBid;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getHighestBidder() {
		return highestBidder;
	}
	public void setHighestBidder(String highestBidder) {
		this.highestBidder = highestBidder;
	}
	public String getAuctionEndTime() {
		return auctionEndTime;
	}
	public void setAuctionEndTime(String auctionEndTime) {
		this.auctionEndTime = auctionEndTime;
	}
	public int getShippingTime() {
		return shippingTime;
	}
	public void setShippingTime(int shippingTime) {
		this.shippingTime = shippingTime;
	}
	public double getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	public double getExpeditedCost() {
		return expeditedCost;
	}
	public void setExpeditedCost(double expeditedCost) {
		this.expeditedCost = expeditedCost;
	}
	
    
    
    
}
