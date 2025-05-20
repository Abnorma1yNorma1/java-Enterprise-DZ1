package by.it_academy.jd2.Mk_jd2_111_25;

public class Main {
    public static void main(String[] args) {
        int[] testCases1 = {-342_221_614, 10_000_001, 100_000, 2_000, 14_000};
        for (int number: testCases1){
            System.out.println(Namer.convertIntToString(number, false));
        }
        double[] testCases2 = {48.02, 1.01, 2.2, 12.13, 4.3};
        for (double number: testCases2){
            System.out.println(Namer.convertDoubleToString(number));
        }
        int[] testCases3 = {14, 253, 2};
        for (int days: testCases3){
            System.out.println(Namer.stringDaysToWeek(days));
        }

    }
}