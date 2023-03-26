import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// a custom exception made by us which will be thrown when wrong/invalid choice is given
class WrongOptionException extends Exception {
    WrongOptionException() {}

    public String toString() {
        return "Wrong option , please try again";
    }
}

public class FileOperations {
    //we are using relative path here.
    private static final String INPUT_FILE_NAME = "src/in.txt";

    // a function check valid options
    static void checkOptionValidity(int option) throws WrongOptionException {
        if (option <= 0 || option > 5) throw new WrongOptionException();
    }

    //this function is used to add new car to our file
    public static void addCar(List<Car> carList) {
        int option;
        String regNumber;
        int yearMade;
        String[] color = new String[3];
        String carMaker;
        String carModel;
        int price;
        Scanner carAddScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter the following info or enter q to go back to main menu.");
            System.out.println("Enter Car Reg no.(It should be unique)");
            regNumber = carAddScanner.nextLine();
            if (regNumber.equalsIgnoreCase("q")) {
                return;
            }
            // searching if the reg number already exists in our car list using isDuplicate() function
            boolean duplicateRegFound = isDuplicateFound(carList, regNumber);
            if (duplicateRegFound) {
                // reg number already exist so trying again in next loop
                continue;
            }

            System.out.println("Enter Year of made");
            yearMade = carAddScanner.nextInt();
            carAddScanner.nextLine();

            System.out.println("Enter Car colors(Max 3)");
            color[0] = carAddScanner.nextLine();
            color[1] = carAddScanner.nextLine();
            color[2] = carAddScanner.nextLine();

            System.out.println("Enter Car maker");
            carMaker = carAddScanner.nextLine();

            System.out.println("Enter Car model");
            carModel = carAddScanner.nextLine();

            System.out.println("Enter Car price");
            price = carAddScanner.nextInt();
            carAddScanner.nextLine();

            // creating car object from input data. then adding to car list
            Car c1 = new Car(regNumber, yearMade, color, carMaker, carModel, price);
            carList.add(c1);

            // then saving our file using writeToFile() function
            writeToFile(carList);
            System.out.println("Your car " + c1 + " has been Added.");

            System.out.println("(1)Do You wish to add another?");
            System.out.println("(2)Go back to main menu");
            option = carAddScanner.nextInt();
            carAddScanner.nextLine();
            if (option == 2) break;
            else if (option != 1) System.out.println("Invalid input,try again");

        }
    }

    //we use this to delete existing cars from file
    public static void deleteCar(List<Car> carList) {
        int option;
        while (true) {
            System.out.println("Please enter the Registry number or enter q to go back to main menu.");
            Scanner carDeleteScanner = new Scanner(System.in);
            String regNumber = carDeleteScanner.nextLine();
            if (regNumber.equalsIgnoreCase("q")) {
                return;
            }
            // searching for car by reg number
            int searchIndex = findByRegNumber(carList, regNumber);
            // if it exists, remove from list
            if (searchIndex != -1) {
                carList.remove(searchIndex);
            }
            else {
                System.out.println("No Such Registry Number exist.Please try again");
                continue;
            }
            // then updating our file using writeToFile() function
            writeToFile(carList);
            System.out.println("Your Car has been Deleted.");

            System.out.println("(1)Do You wish to delete another?");
            System.out.println("(2)Go back to main menu");
            option = carDeleteScanner.nextInt();
            carDeleteScanner.nextLine();
            if (option == 2) break;
            else if (option != 1) System.out.println("Invalid input,try again");
        }
    }

    // it is used to search existing cars
    public static void searchCar(List<Car> carList) throws WrongOptionException {
        int option;
        while (true) {
            System.out.println("(1) By Registration Number");
            System.out.println("(2) By Car Manufacturer and Car Model");
            System.out.println("(3) Back to Main Menu");
            Scanner carSearchScanner = new Scanner(System.in);
            try {
                // checking if user gives invalid options
                option = carSearchScanner.nextInt();
            } catch (Exception e) {
                WrongOptionException exception = new WrongOptionException();
                System.out.println(exception);
                continue;
            }
            carSearchScanner.nextLine();
            if (option == 3) return;
            else if (option == 1) {
                while (true) {
                    System.out.println("Please enter the Registry number or enter q to go back to previous menu.");
                    String regNumber = carSearchScanner.nextLine();
                    if (regNumber.equalsIgnoreCase("q")) {
                        break;
                    }
                    // similar work when searching for car reg in delete feature above
                    int searchIndex = findByRegNumber(carList, regNumber);
                    if (searchIndex != -1) {
                        System.out.println(carList.get(searchIndex).toStringPretty());

                    } else {
                        System.out.println("No Such Registry Number exist.Please try again");
                    }
                }

            } else if (option == 2) {

                while (true) {

                    System.out.println("Enter Car Manufacturer, write 'any' for any manufacturer or enter q to go back to previous menu.");
                    String manufacturer = carSearchScanner.nextLine();
                    if (manufacturer.equalsIgnoreCase("q")) {
                        break;
                    }
                    System.out.println("Enter Car Model, write 'any' for any model or enter q to go back to previous menu.");
                    String model = carSearchScanner.nextLine();
                    if (model.equalsIgnoreCase("q")) {
                        break;
                    }
                    if (manufacturer.equalsIgnoreCase("any") && model.equalsIgnoreCase("any")) {
                        // both cant be any
                        System.out.println("Both Manufacturer and Model can't be any");
                    } else if (manufacturer.equalsIgnoreCase("any") && !model.equalsIgnoreCase("any")) {

                        // if only model is specified, then find all the cars with the model
                        List<Integer> indexListOfModels = findByModel(carList, model);

                        if (indexListOfModels.size() < 1) {
                            System.out.println("No such Car model exist.Please try again");
                            continue;
                        }
                        for (Integer modelIndex : indexListOfModels) {
                            System.out.println(carList.get(modelIndex).toStringPretty());
                        }

                    } else if (!manufacturer.equalsIgnoreCase("any") && model.equalsIgnoreCase("any")) {

                        // if only manufacturer is specified, find all car with that manufacturer
                        List<Integer> indexListOfManufacturers = findByManufacturer(carList, manufacturer);

                        if (indexListOfManufacturers.size() < 1) {
                            System.out.println("No such Car Manufacturer exist.Please try again");
                            continue;
                        }
                        for (Integer manufacturerIndex : indexListOfManufacturers) {
                            System.out.println(carList.get(manufacturerIndex).toStringPretty());
                        }

                    } else {
                        // this is last option where both model and manufacturer specified
                        // finding list of cars where both these two things match
                        List<Integer> indexListOfModelAndManufacturers = findByManufacturerAndModel(carList, manufacturer, model);

                        if (indexListOfModelAndManufacturers.size() < 1) {
                            System.out.println("No Such Car with given model and manufacturer exist.Please try again");
                            continue;
                        }
                        for (Integer manufacturerModelIndex : indexListOfModelAndManufacturers) {
                            System.out.println(carList.get(manufacturerModelIndex).toStringPretty());
                        }
                    }
                }
            }
            else {
                throw new WrongOptionException();
            }
        }
    }

    //duplicate reg number checker utility function
    private static boolean isDuplicateFound(List<Car> carList, String carRegNumber) {
        boolean duplicateFound = false;
        for (Car car : carList) {
            if (car.getRegNumber().equals(carRegNumber)) {
                System.out.println("This Registry Number exist.Please try again");
                duplicateFound = true;
                break;
            }
        }
        return duplicateFound;
    }

    // only returns one index as we know reg number is unique
    private static int findByRegNumber(List<Car> carList, String carRegNumber) {
        int searchIndex = -1;
        for (int i = 0; i < carList.size(); i++) {
            Car c = carList.get(i);
            if (c.getRegNumber().equals(carRegNumber)) {
                searchIndex = i;
            }
        }
        return searchIndex;
    }

    // returns list of index as there can be multiple cars with same model
    private static List<Integer> findByModel(List<Car> carList, String carModel) {
        List<Integer> searchIndexArray = new ArrayList<>();
        for (int i = 0; i < carList.size(); i++) {
            Car c = carList.get(i);
            if (c.getModel().equals(carModel)) {
                searchIndexArray.add(i);
            }
        }
        return searchIndexArray;
    }

    // returns list of index as there can be multiple cars with same manufacturer
    private static List<Integer> findByManufacturer(List<Car> carList, String carManufacturer) {
        List<Integer> searchIndexArray = new ArrayList<>();
        for (int i = 0; i < carList.size(); i++) {
            Car c = carList.get(i);
            if (c.getManufacturer().equals(carManufacturer)) {
                searchIndexArray.add(i);
            }
        }
        return searchIndexArray;
    }

    // returns list of index as there can be multiple cars with same model and manufacturer
    private static List<Integer> findByManufacturerAndModel(List<Car> carList, String manufacturer, String model) {
        List<Integer> searchIndexArray = new ArrayList<>();
        for (int i = 0; i < carList.size(); i++) {
            Car c = carList.get(i);
            if (c.getManufacturer().equals(manufacturer) && c.getModel().equals(model)) {
                searchIndexArray.add(i);
            }
        }
        return searchIndexArray;
    }

    // a simple file read function
    private static List<Car> readFromFile() {
        List<Car> carList = new ArrayList<>();
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            System.out.println("Reading From Car Database");
            int serial = 0;
            while (true) {
                line = br.readLine();
                if (line != null) {
                    serial++;

                    ///parsing the line as string then separating parts of the string by comma
                    String[] temp = line.split(",");
                    String carReg;
                    carReg = temp[0];
                    int yearMade;
                    yearMade = Integer.parseInt(temp[1]);
                    String color1;
                    color1 = temp[2];
                    String color2;
                    color2 = temp[3];
                    String color3;
                    color3 = temp[4];
                    String carMake;
                    carMake = temp[5];
                    String carModel;
                    carModel = temp[6];
                    int price;
                    price = Integer.parseInt(temp[7]);
                    String[] color = new String[3];
                    color[0] = color1;
                    color[1] = color2;
                    color[2] = color3;

                    //after getting all the data, making a car object and adding it to car list
                    Car car = new Car(carReg, yearMade, color, carMake, carModel, price);
                    System.out.print("Car No." + serial + " ");
                    // using toStringPretty to print data in understandable format
                    System.out.println(car.toStringPretty());
                    carList.add(car);
                } else break;

            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return carList;
    }

    //simple function that writes the carlist into file
    private static void writeToFile(List<Car> carList) {
        String carInfoInString;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME));
            for (Car car : carList) {
                // iterating through the carlist then saving the data in comma separated format using toString()
                carInfoInString = car.toString();
                bw.write(carInfoInString);
                bw.write("\n");
            }
            bw.close();
            System.out.println("Car Database Updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //our main function where the program starts and executes
    public static void main(String[] args) {

        int option;
        // at very first reading the file and adding the car info to car list
        List<Car> carList = readFromFile();
        while (true) {

            Scanner mainFunctionScanner = new Scanner(System.in);
            String[] optionMenu = new String[]{
                    "Enter your Option:",
                    "(1) Search Cars",
                    "(2) Add Car",
                    "(3) Delete Car",
                    "(4) Show All Cars In Database",
                    "(5) Exit System"};
            // printing the options for users
            for (int i = 0; i < 6; i++) {
                System.out.println(optionMenu[i]);
            }

            try {
                // checking if user gives invalid options
                option = mainFunctionScanner.nextInt();
            } catch (Exception e) {
                WrongOptionException exception = new WrongOptionException();
                System.out.println(exception);
                continue;
            }

            System.out.println("your Option is :" + option);

            // different options are executed by their respective functions
            try {
                // checking if the option is valid
                checkOptionValidity(option);
                switch (option) {
                    case 1:
                        searchCar(carList);
                        break;
                    case 2:
                        addCar(carList);
                        break;
                    case 3:
                        deleteCar(carList);
                        break;
                    case 4:
                        readFromFile();
                        break;
                    case 5:
                        return;
                    default:
                        throw new WrongOptionException();
                }

            } catch (WrongOptionException e) {
                System.out.println(e);
            }
        }
    }
}