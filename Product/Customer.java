package Product;
import Product.DeliveryPoint;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int id;
    private String name;
    private String login;
    private String password;
    private List<DeliveryPoint> orders;

    public Customer() {
        orders = new ArrayList<>();
    }

    public Customer(int id, @NotNull String name, @NotNull String login, @NotNull String password) throws IncorrectConstructorParameters {
        this.id = id();
        this.name = name;
        this.login = login;
        this.password = password;
        orders = new ArrayList<>();
    }

    // -----------SETTERS AND GETTERS------------

    public UUID getId() {
        return id;
    }

    public void setId(int id) throws IncorrectConstructorParameters {
        if (id >= 0)
            this.id = id;
         else throw new IncorrectConstructorParameters("Incorrect id ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //----------------методы----------------

    public int size() {
        return orders.size();
    }

    public DeliveryPoint get(int index) {
        return orders.get(index);
    }

    public void add(int index,  DeliveryPoint deliveryPoint) {
        orders.add(index, deliveryPoint);
    }

    public DeliveryPoint remove(int index) {
        return orders.remove(index);
    }

}
