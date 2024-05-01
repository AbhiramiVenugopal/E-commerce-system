package proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TransactionDAO {

	public Transaction read(int tranID) {
		String sql = "SELECT CreditCardNumber, CardHolderName, ExpirationDate, SecurityCode, "
				+ "ItemID, UserID, TotalCost, Seller FROM Transactions WHERE TransactionID = ?";
		Transaction tr = null;
		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, tranID);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					tr = new Transaction();
					tr.setTransactionID(tranID);
					tr.setCardNumber(rs.getLong("CreditCardNumber"));
					tr.setCardHolderName(rs.getString("CardHolderName"));
					tr.setExpDate(rs.getInt("ExpirationDate"));
					tr.setCvv(rs.getInt("SecurityCode"));
					tr.setItemID(rs.getInt("ItemID"));
					tr.setUserID(rs.getInt("UserID"));
					tr.setTotalCost(rs.getDouble("TotalCost"));
					tr.setSeller(rs.getString("Seller"));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return tr;
	}

	public Transaction create(Transaction tr) {
		String sql = "INSERT INTO Transactions(CreditCardNumber, CardHolderName, ExpirationDate, SecurityCode, "
				+ "ItemID, UserID, TotalCost, Seller, TransactionID) VALUES(?,?,?,?,?,?,?,?,?)";
		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, tr.getCardNumber());
			pstmt.setString(2, tr.getCardHolderName());
			pstmt.setInt(3, tr.getExpDate());
			pstmt.setInt(4, tr.getCvv());
			pstmt.setInt(5, tr.getItemID());
			pstmt.setInt(6, tr.getUserID());
			pstmt.setDouble(7, tr.getTotalCost());
			pstmt.setString(8, tr.getSeller());

			Random ran = new Random();
			int id = ran.nextInt(100000);
			tr.setTransactionID(id);
			pstmt.setInt(9, tr.getTransactionID());
			System.out.println("New Transaction created with ID:" + id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return tr;
	}

	public Transaction update(int id, Transaction card) {
		String sql = "UPDATE Transactions SET CreditCardNumber = ?, CardHolderName = ?, ExpirationDate = ?, SecurityCode = ?, ItemID = ?, UserID = ?, TotalCost = ?, Seller = ? WHERE TransactionID =?";
		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, card.getCardNumber());
			pstmt.setString(2, card.getCardHolderName());
			pstmt.setInt(3, card.getExpDate());
			pstmt.setInt(4, card.getCvv());
			pstmt.setInt(5, card.getItemID());
			pstmt.setInt(6, card.getUserID());
			pstmt.setDouble(7, card.getTotalCost());
			pstmt.setString(8, card.getSeller());
			pstmt.setInt(9, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return card;
	}

	public void delete(int number) {
		String sql = "DELETE FROM Transactions WHERE TransactionID = ?";
		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, number);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}