import java.util.*;

public class Input {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();

    String[] parts = input.split("|");
 


System.out.println("Flight Number: " + parts[0]);
System.out.println("Flight Name: " + parts[1]);
System.out.println("Passenger Count: " + Integer.parseInt(parts[2]));
System.out.println("Current Fuel: " + Double.parseDouble(parts[3]));
    }

}
