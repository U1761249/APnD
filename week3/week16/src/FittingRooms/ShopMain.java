package FittingRooms;


public class ShopMain {



    public static void main(String[] args) {
        Shop shop = new Shop();
        for (int i = 0; i < 10; i++){
            shop.addCustomer(new Customer(i));
        }
        shop.run();
    }
}
