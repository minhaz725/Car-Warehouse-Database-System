import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WrIndxException extends Exception {
    // private int detail;

    WrIndxException() {}


    public String toString() {
        return "Wrong choice , please try again";
    }
}




public class FileOperations {

    private static final String INPUT_FILE_NAME = "C:\\Users\\Minhaz\\Desktop\\CODES\\untitled\\src\\in.txt";
    //private static final String OUTPUT_FILE_NAME = "C:\\Users\\Minhaz\\Desktop\\CODES\\untitled\\src\\out.txt";

    static void indx(int x) throws WrIndxException {
        if (x <= 0 || x > 4) throw new WrIndxException();
    }


    static int adder(List<Car> carList) {



        int y;
        while (true) {



            System.out.println("Please enter the following infos:");
            String carReg;
            int yearMade;
            String color1;
            String color2;
            String color3;
            String carMake;
            String carModel;
            int price;
            Scanner carvar = new Scanner(System.in);
            System.out.println("Enter Car Reg no.(It should be unique)");
            while (true) {

                Scanner regisearch = new Scanner(System.in);
                String searchName =regisearch.nextLine() ;
                carReg=searchName;
                int searchIndex = -1;
                for (int i = 0; i < carList.size(); i++) {
                    Car c = carList.get(i);
                    if (c.getCarReg().equals(searchName)) {
                        searchIndex = i;
                    }
                }

                if (searchIndex != -1) {
                    System.out.println("This Registry Number exist.Please try again");
                    continue;
                }
                else break;
            }

            System.out.println("Enter Year of made");
            yearMade = carvar.nextInt();
            carvar.nextLine();
            System.out.println("Enter Car colors(Max 3)");
            color1 = carvar.nextLine();

            color2 = carvar.nextLine();

            color3 = carvar.nextLine();
            System.out.println("Enter Car maker");
            carMake = carvar.nextLine();
            System.out.println("Enter Car model");
            carModel = carvar.nextLine();
            System.out.println("Enter Car price");
            price = carvar.nextInt();
            carvar.nextLine();

            Car c1 = new Car(carReg, yearMade, color1, color2, color3, carMake, carModel, price);
            carList.add(c1);

            System.out.println("Your car " + c1 + " has been Added.");
            while (true) {
                System.out.println("(1)Do You wish to add another?");
                System.out.println("(2)Go back to main menu");
                y = carvar.nextInt();
                if (y == 1) break;
                else if (y == 2) break;
                else {
                    System.out.println("Invalid input,try again");
                    continue;
                }
            }
            if (y == 1) continue;
            else if (y == 2) break;
        }
        return y;
    }




    static int deleter(List<Car> carList)
    {
        int y;
        while (true){

        System.out.println("Enter Car Registry Number:");
        Scanner regisearch = new Scanner(System.in);
        String searchName =regisearch.nextLine() ;
        int searchIndex = -1;
        for (int i = 0; i < carList.size(); i++) {
            Car c = carList.get(i);
            if (c.getCarReg().equals(searchName)) {
                searchIndex = i;
            }
        }
        if (searchIndex != -1) {
            carList.remove(searchIndex);
        }
        else {
            System.out.println("No Such Registry Number exist.Please try again");
            continue;
        }
        System.out.println("Your Car has been Deleted.");

        while (true) {
            System.out.println("(1)Do You wish to delete another?");
            System.out.println("(2)Go back to main menu");
            y = regisearch.nextInt();
            if (y == 1) break;
            else if (y == 2) break;
            else {
                System.out.println("Invalid input,try again");
                continue;
                }
            }
        if (y == 1) continue;
        else if (y == 2) break;

        }

        return y;
    }


    static int searcher(List<Car> carList)
    {
        int y;


        while (true) {
            System.out.println("(1) By Registration Number");
            System.out.println("(2) By Car Make and Car Model");
            System.out.println( "(3) Back to Main Menu");
            Scanner option= new Scanner(System.in);
            y = option.nextInt();
            if (y == 3) return y;
            else if (y == 1)
            {


                while (true) {

                    int p;
                    System.out.println("Enter Car Registry Number:");
                    Scanner regisearch = new Scanner(System.in);
                    String searchName = regisearch.nextLine();
                    int searchIndex = -1;
                    for (int i = 0; i < carList.size(); i++) {
                        Car c = carList.get(i);
                        if (c.getCarReg().equals(searchName)) {
                            searchIndex = i;
                        }
                    }
                    if (searchIndex != -1) {
                        System.out.println(carList.get(searchIndex));

                    } else {
                        System.out.println("No Such Registry Number exist.Please try again");
                        continue;
                    }
                }


            }


            else if (y == 2) {

                while (true) {

                    int p;
                    System.out.println("Enter Car Maker:");
                    Scanner search = new Scanner(System.in);
                    String mak_searchName = search.nextLine();
                    System.out.println("Enter Car Model:");
                    String mod_searchName = search.nextLine();
                    if (mak_searchName.equalsIgnoreCase("any") == true &&
                            mod_searchName.equalsIgnoreCase("any") == true) continue;
                    else if (mak_searchName.equalsIgnoreCase("any") == true &&
                            mod_searchName.equalsIgnoreCase("any") == false) {

                        int searchIndex = -1;
                        for (int i = 0; i < carList.size(); i++) {
                            Car c = carList.get(i);
                            if (c.getCarReg().equals(mak_searchName)) {
                                searchIndex = i;
                            }
                        }
                        if (searchIndex != -1) {
                            System.out.println("No such Car maker exist.Please try again");
                            continue;
                        } else {
                            for (int i = 0; i < carList.size(); i++)
                                if (carList.get(i).getCarMake().equalsIgnoreCase(mak_searchName) == true) {
                                    System.out.println(carList.get(i));
                                }
                        }
                    } else if (mak_searchName.equalsIgnoreCase("any") == false &&
                            mod_searchName.equalsIgnoreCase("any") == true) {

                        int searchIndex = -1;
                        for (int i = 0; i < carList.size(); i++) {
                            Car c = carList.get(i);
                            if (c.getCarReg().equals(mod_searchName)) {
                                searchIndex = i;
                            }
                        }
                        if (searchIndex != -1) {
                            System.out.println("No such Car model exist.Please try again");
                            continue;
                        } else {
                            for (int i = 0; i < carList.size(); i++)
                                if (carList.get(i).getCarModel().equalsIgnoreCase(mod_searchName) == true) {
                                    System.out.println(carList.get(i));
                                }
                        }
                    } else {

                        int searchIndex1 = -1;
                        int searchIndex2 = -1;
                        for (int i = 0; i < carList.size(); i++) {
                            Car c = carList.get(i);
                            if (c.getCarReg().equals(mak_searchName)) {
                                searchIndex1 = i;
                            }
                        }
                        for (int i = 0; i < carList.size(); i++) {
                            Car c = carList.get(i);
                            if (c.getCarReg().equals(mod_searchName)) {
                                searchIndex2 = i;
                            }
                        }


                        if (searchIndex1 == -1 || searchIndex2 == -1 || searchIndex1 != searchIndex2) {
                            System.out.println("No such Car exist.Please try again");
                            continue;
                        } else System.out.println(carList.get(searchIndex1));
                    }

                }

            }
            int p;
            while (true) {

                System.out.println("(1)Do You wish to Search again?");
                System.out.println("(2)Go back to menu");
                p = option.nextInt();
                if (p == 1) break;
                else if (p == 2) break;
                else {
                    System.out.println("Invalid input,try again");
                    continue;
                }
            }
            if (p == 1) continue;
            else if (p == 2) break;
        }
        return y;
    }







    public static void main(String[] args) {

        int x;
        List<Car> carList = new ArrayList();
        List<String> carString = new ArrayList();



        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            while (true) {
                line = br.readLine();
                if (line != null){

                String[] temp = line.split(",");
                String carReg;
                carReg = temp[0];
                    System.out.println(temp[0]);
                int yearMade;
                yearMade = Integer.parseInt(temp[1]);
                    System.out.println(yearMade);
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
                Car c1 = new Car(carReg, yearMade, color1, color2, color3, carMake, carModel, price);
                carList.add(c1);
            }
                else break;




                System.out.println(line);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



        System.out.println( carList.get(1));









        while (true) {
            try {

                BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

                String[] option = new String[]{
                        "Enter your Option:",
                        "(1) Search Cars",
                        "(2) Add Car",
                        "(3) Delete Car",
                        "(4) Exit System"};
                for (int i = 0; i < 5; i++) {
                    System.out.println(option[i]);
                }
                String number = buf.readLine();
                System.out.println("your Option is :" + number);
                char[] a = number.toCharArray();
                x = a[0] - 48;
                //System.out.println(x);
                try {
                    indx(x);
                    if (x == 1)
                    { int t=searcher(carList);
                        if(t==3) continue;
                    }
                    if (x == 2) {
                        int t=adder(carList);
                        if(t==2) continue;
                    }
                    if (x == 3) {
                        int t=deleter(carList);
                        if (t==2) continue;
                    }
                    if (x == 4) return;
                    if (x > 0 && x <= 4) break;
                } catch (WrIndxException e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


//        System.out.println(carList.toString());
        String text;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME));
            for (int i=0;i< carList.size();i++) {
                text = carList.get(i).toString();
                bw.write(text);
                bw.write("\n");
            }
            bw.close();
            System.out.println("File written");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}