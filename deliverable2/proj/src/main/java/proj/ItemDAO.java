package proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public List<Item> readAll() {
        String sql = "SELECT * FROM Inventory"; // Changed from Items to Inventory
        List<Item> items = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Item item = createItemFromResultSet(rs);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public Item read(int itemId) {
        String sql = "SELECT * FROM Inventory WHERE ItemID = ?"; // Changed from Items to Inventory
        Item item = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itemId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    item = createItemFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    public List<Item> readByKeyword(String keyword) {
        String sql = "SELECT * FROM Inventory WHERE Description LIKE ?"; // Adjusted the column name
        List<Item> items = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Item item = createItemFromResultSet(rs);
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public boolean create(Item item) {
        String sql = "INSERT INTO Inventory (Name, Description, AuctionType, Price, Seller, AuctionEndTime, ShippingTime, ShippingPrice, ExpeditedCost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setString(3, item.getAuctionType());
            stmt.setDouble(4, item.getPrice());
            stmt.setString(5, item.getSeller());
            stmt.setString(6, item.getAuctionEndTime());
            stmt.setInt(7, item.getShippingTime());
            stmt.setDouble(8, item.getShippingPrice());
            stmt.setDouble(9, item.getExpeditedCost());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int itemId) {
        String sql = "DELETE FROM Inventory WHERE ItemID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itemId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateBid(int itemId, double newBidAmount, String newBidder) {
        String sql = "UPDATE Inventory SET HighestBid = ?, HighestBidder = ? WHERE ItemID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newBidAmount);
            stmt.setString(2, newBidder);
            stmt.setInt(3, itemId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Item createItemFromResultSet(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setItemID(rs.getInt("ItemID"));
        item.setName(rs.getString("Name"));
        item.setDescription(rs.getString("Description"));
        item.setAuctionType(rs.getString("AuctionType"));
        item.setPrice(rs.getDouble("Price"));
        item.setHighestBid(rs.getDouble("HighestBid"));
        item.setSeller(rs.getString("Seller"));
        item.setHighestBidder(rs.getString("HighestBidder"));
        item.setAuctionEndTime(rs.getString("AuctionEndTime"));
        item.setShippingTime(rs.getInt("ShippingTime"));
        item.setShippingPrice(rs.getDouble("ShippingPrice"));
        item.setExpeditedCost(rs.getDouble("ExpeditedCost"));
        return item;
    }
}
