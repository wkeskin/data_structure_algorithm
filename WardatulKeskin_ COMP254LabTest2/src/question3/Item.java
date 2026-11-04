package question3;

public class Item {
	int itemId;
	String itemName;
	double itemPrice;

	public Item(int itemId, String itemName, double itemPrice) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public String toString() {
		return String.format("[%d - %s: $%.2f]", itemId, itemName, itemPrice);
	}
}
