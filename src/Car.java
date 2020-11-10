public class Car{
    private String CarReg;
    private int YearMade;
    private String Color1;
    private String Color2;
    private String Color3;
    private String CarMake;
    private String CarModel;
    private int Price;

    public String getCarReg() {
        return CarReg;
    }

    public int getYearMade() {
        return YearMade;
    }

    public String getColor1() {
        return Color1;
    }

    public String getColor2() { return Color2; }

    public String getColor3() { return Color3;}

    public String getCarMake() {
        return CarMake;
    }

    public String getCarModel() {
        return CarModel;
    }

    public int getPrice() {
        return Price;
    }



    public Car(String carReg, int yearMade, String color1,String color2,String color3, String carMake, String carModel, int price) {
        CarReg = carReg;
        YearMade = yearMade;
        Color1 = color1;
        Color2 = color2;
        Color3 = color3;
        CarMake = carMake;
        CarModel = carModel;
        Price = price;
    }

    public String toString()
    {
        return CarReg+","+YearMade+","+Color1+","+Color2+","+Color3+","+CarMake+","+CarModel+","+Price;
    }


}
