public class Dessert {
    int flavor;
    int price;
    static int numDesserts = 0;

    public Dessert(int flavor, int price) {
        this.flavor = flavor;
        this.price = price;
        Dessert.numDesserts += 1;
    }
    public void printDessert() {
        System.out.print(this.flavor + " ");
        System.out.print(this.price + " ");
        System.out.print(Dessert.numDesserts);
    }
    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
