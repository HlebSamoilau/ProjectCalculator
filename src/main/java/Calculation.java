import java.io.IOException;

public class Calculation {
    public static void main(String[] args) {
        Calculator calculator = Calculator.getNewCalculator();
        try{
            calculator.readFromFile(calculator.getPathToFile());
            System.out.println(calculator.calculate());
        }catch (ArithmeticException e){
            System.out.println("Can't divide by 0");
        }catch (NumberFormatException e){
            System.out.println("Invalid instruction format");
        }catch (IOException e){
            System.out.println("Invalid path to file");
        }
    }
}
