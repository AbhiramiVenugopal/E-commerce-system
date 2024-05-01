package proj;

public class BidRequest {
    private int itemId;
    private double bidAmount;
    private String userName;

    // Getters and setters for itemId and bidAmount
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

	public void setUserName(String username) {
		this.userName = username;
	}
	
	public String getUserName() {
		return userName;
	}
}

