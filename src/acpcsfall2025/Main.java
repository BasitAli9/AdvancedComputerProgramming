
package acpcsfall2025;
import java.util.Scanner;

/**
 *
 * @author Basit
 */
public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        int val1, val2, res;
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter first value : ");
        val1 = input.nextInt();

        System.out.print("Please enter second value : ");
        val2 = input.nextInt();

        res = val1 + val2;

        System.out.println("The sum of "+val1+" & "+val2+" is equal to "+res);
    }

}