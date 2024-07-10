//IIT No: 20220332
//UoW No: w1953207
//Name  : D. K. J. S. Dewmina

//implementing electronics child class of product class
public class Electronics extends Product{
    //encapsulating class attributes
private final String brand;
private final String warrantyPeriod;
//creating class constructor
    public Electronics(String productID, String productName, int availableAmount, double prc, String brand, String warrantyPeriod) {
        super(productID, productName, availableAmount, prc);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }
    //overriding superclass methods
    @Override
    public String getInfo(){
        return brand+", "+warrantyPeriod+" warranty";
    }
    @Override
    public String getType(){
        return "Electronics";
    }
    @Override
    public String getProductDetails(){
        return "Product Id: "+ getProductID()+"\nCategory: Clothing\n" +
                "Name: "+getProductName()+"\n" +
                "Brand: "+brand+"\nItem warranty: "+warrantyPeriod+"\nItems Available: "+getAvailableAmount();
    }
    //Overriding default toString method
    @Override
    public String toString(){
        return super.toString()+"Electronic brand: "+this.brand+"\n" +
                "Warranty period : "+this.warrantyPeriod+"\n";
    }
}
