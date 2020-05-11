package Product;
import org.jetbrains.annotations.NotNull;
import java.util.Formatter;
import java.io.*;
import java.text.ParseException;


public class Product implements Serializable {

    private String productName; //наименование
    private double productPrice; //цена
    private int numberOfProduct; //количество
    private String dateOfDelivery; //дата поступления

    //------------------------getters and setters-----------------------

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public String getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setProductName(String productName) {
        //check
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public void setDateOfDelivery(String dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    //---------------------конструктор----------------------------------

    private Product() {
    }

    public Product(String productName, double productPrice, int numberOfProduct, String dateOfDelivery) throws IllegalArgumentException {

        if (productName == "")
            throw new IllegalArgumentException("Price can't be less then zero");
        //set
        if (productPrice <= 0)
            throw new IllegalArgumentException("Price can't be less then zero");
        if (numberOfProduct <= 0)
            throw new IllegalArgumentException("Number of products can't be less then zero");

        this.productName=productName;
        this.productPrice=productPrice;
        this.numberOfProduct=numberOfProduct;
        this.dateOfDelivery=dateOfDelivery;
    }

    //--------------------запись и чтение----------------------------

    public String toString() {
        Formatter ans = new Formatter();
        ans.format("productName: %s ", productName);
        ans.format("numberOfProduct: %d ", numberOfProduct);
        ans.format("productPrice: %f ", productPrice);
        ans.format("dateOfDelivery: %s", dateOfDelivery);
        return ans.toString();
    }

    public static void writeProduct(Writer out, Product product, char delimiter) {
        try {
            out.write(product.getProductName() + " , " +
                    product.getNumberOfProduct() + " , " +
                    product.getProductPrice() + " , " +
                    product.getDateOfDelivery() + delimiter);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public static Product readProduct(@NotNull Reader in, @NotNull char delimiter) throws IOException, ParseException {
        Product product = new Product();
            StringBuilder temp = new StringBuilder();
            char curChar;
            //читаем в буффер по символу, пока не найдем символ перевода строки
            while ((curChar = (char) in.read()) != delimiter) {
                temp.append(curChar);
            }
            String[] fields = temp.toString().split(" , ");
            product.productName = fields[0];
            product.numberOfProduct = Integer.parseInt(fields[1]);
            product.productPrice = Double.parseDouble(fields[2]);
            product.dateOfDelivery = fields[3];

        return product;
    }
}

