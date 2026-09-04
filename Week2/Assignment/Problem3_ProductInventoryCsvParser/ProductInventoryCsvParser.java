public class ProductInventoryCsvParser {

    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }

        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String productName = fields[0].trim();
        String sku = fields[1].trim();
        String quantity = fields[2].trim();

        System.out.printf("Product: %s | SKU: %s | Qty: %s%n", productName, sku, quantity);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        parseInventoryRecord("Wireless Mouse,WM-2201,150");

        System.out.println("\n--- Test Case 2 ---");
        parseInventoryRecord("Wireless Mouse,150");
    }
}



