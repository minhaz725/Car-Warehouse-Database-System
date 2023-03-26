public class Car{
    //define variables for car class
    //names must start with lowercase letter and it should be easy to understand (e.g. avoid x,y type names)
    private String regNumber;
    private int yearMade;
    private String color1;
    private String color2;
    private String color3;
    private String manufacturer;
    private String model;
    private int price;

    //getters and setters
    public String getRegNumber() {
        return regNumber;
    }

    public int getYearMade() {
        return yearMade;
    }

    public String getColor1() {
        return color1;
    }

    public String getColor2() { return color2; }

    public String getColor3() { return color3; }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getModel() {
        return model;
    }

    public int getPrice() { return price; }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //car constructor
    public Car(String regNumber, int yearMade, String []color, String manufacturer, String model, int price) {
        this.regNumber = regNumber;
        this.yearMade = yearMade;
        color1 = color[0];
        color2 = color[1];
        color3 = color[2];
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
    }

    // default toString method which is basically comma separated fields of a car object
    // we use this format to easily store in and read from file
    public String toString()
    {
        return regNumber +","+
                yearMade +","+
                color1 +","+ color2 +","+ color3 +","+
                manufacturer +","+
                model +","+
                price;
    }

    // we use it to display car data. it is almost same as toString() function above
    // just difference is it's a bit elaborated
    public String toStringPretty()
    {
        return "Registration Number: "+ regNumber +", "+
                "Year Made: "+ yearMade +", "+
                "Colors: "+ color1 +","+ color2 +","+ color3 +", "+
                "Manufacturer: "+ manufacturer +", "+
                "Model: "+ model +", "+
                "Price: "+ price;
    }


}
