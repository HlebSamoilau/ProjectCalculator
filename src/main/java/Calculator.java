import java.io.*;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Calculator {
    private Deque<String> nameOfInstructions;
    private Deque<String> valueForInstructions;
    private Set<String> instructions;
    private double result = 0;

    private Calculator() {
        nameOfInstructions = new LinkedList<>();
        valueForInstructions = new LinkedList<>();
        instructions = new HashSet<>();
        instructions.add("add");
        instructions.add("divide");
        instructions.add("subtract");
        instructions.add("multiply");
        instructions.add("apply");
    }

    public static Calculator getNewCalculator() {
        return new Calculator();
    }

    public String getPathToFile() throws IOException {
        String path = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Please enter path to file");
            path = reader.readLine();
        }
        return path;
    }

    public void readFromFile(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(" ");
                if (instructions.contains(arr[0].toLowerCase())) {
                    nameOfInstructions.add(arr[0].toLowerCase());
                    valueForInstructions.add(arr[1]);
                } else {
                    throw new NumberFormatException();
                }
            }
        }
    }

    private void initFirstValue() {
        if (nameOfInstructions.getLast().equals("apply")) {
            result = Double.parseDouble(valueForInstructions.getLast());
            nameOfInstructions.removeLast();
            valueForInstructions.removeLast();
        } else {
            throw new NumberFormatException();
        }
    }

    public double calculate() {
        initFirstValue();
        while (!nameOfInstructions.isEmpty()) {
            switch (nameOfInstructions.getFirst()) {
                case ("add"):
                    result += Double.parseDouble(valueForInstructions.getFirst());
                    nameOfInstructions.removeFirst();
                    valueForInstructions.removeFirst();
                    break;
                case ("subtract"):
                    result -= Double.parseDouble(valueForInstructions.getFirst());
                    nameOfInstructions.removeFirst();
                    valueForInstructions.removeFirst();
                    break;
                case ("multiply"):
                    result *= Double.parseDouble(valueForInstructions.getFirst());
                    nameOfInstructions.removeFirst();
                    valueForInstructions.removeFirst();
                    break;
                case ("divide"):
                    double divider = Double.parseDouble(valueForInstructions.getFirst());
                    if (divider == 0) {
                        throw new ArithmeticException();
                    } else {
                        result /= divider;
                        nameOfInstructions.removeFirst();
                        valueForInstructions.removeFirst();
                        break;
                    }
            }
        }
        return result;
    }

}

