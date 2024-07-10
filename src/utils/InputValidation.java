package utils;

import java.util.Scanner;

public class InputValidation {


    public static int intInputValidation(){
        Scanner scanner = new Scanner(System.in);
        int convInput;
        while (true){
            try {
                String input = scanner.next();
                convInput = Integer.parseInt(input); // convInput - Converted Input
                break;
            }
            catch (NumberFormatException e){
                System.out.print("Please enter valid input : ");
                scanner.nextLine();
            }
        }
        return convInput;
    }

    public static double doubleInputValidation(){
        Scanner scanner = new Scanner(System.in);
        double convInput;
        while (true){
            try {
                String input = scanner.next();
                convInput = Double.parseDouble(input); // convInput - Converted Input
                break;
            }
            catch (NumberFormatException e){
                System.out.print("Please enter valid input : ");
                scanner.nextLine();
            }
        }
        return convInput;
    }

    public static String stringInputValidation(){
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true){
            input = scanner.next();
            if(input.isEmpty()) {
                System.out.print("Please enter valid input : ");
                scanner.nextLine();
            }
            else{
                break;
            }
        }
        return input;
    }

    public static String warrantyValidation(){
        Scanner scanner = new Scanner(System.in);
        String warrantyPeriod;
        while(true){
            warrantyPeriod = scanner.nextLine();
            //validating user input meets requirements
            if((warrantyPeriod.contains("years" )|| warrantyPeriod.contains("months"))||(warrantyPeriod.contains("weeks" ))){
                break;
            }else{
                System.out.print("Invalid input!\nPlease enter in \"x years\", \"y months\" or \"z weeks\" form : ");
            }
        }
        return warrantyPeriod;
    }
}
