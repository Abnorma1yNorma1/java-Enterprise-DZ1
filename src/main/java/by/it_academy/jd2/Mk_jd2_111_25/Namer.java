package by.it_academy.jd2.Mk_jd2_111_25;

/**
 * Class which provides strings describing number or date
 */
public class Namer {

    private static final String[] zeroToNineteen = {
            "", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять",  "одиннадцать",
            "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать",
            "девятнадцать"
    };

    private static final  String[] tens = {
            "", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    };

    private static final String[] hundreds = {
            "", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"
    };

    private static final String[] millions = {
        "миллион", "миллиона", "миллионов"
    };

    private static final String[] thousands = {
        "тысяча", "тысячи", "тысяч"
    };

    private static final String[] hundredth = {
            "сотая", "сотые", "сотых"
    };

    private static final String[] tenth = {
            "десятая", "десятые" , "десятых"
    };

    /**
     * @param number number to be named
     * @param fem true, if number used to count things in  feminine form
     * @return String of names for each three digit parts of number with rank words and "minus" word in front, if applicable
     */
    public static String convertIntToString(int number, boolean fem){
        StringBuilder builder = new StringBuilder();
        if (number==0) return "ноль";
        if (number<0){
            builder.append("минус ");
        }
        convertThree(Math.abs(number/1_000_000), millions, false, builder);
        builder.append(" ");
        convertThree(Math.abs(number/1_000%1_000), thousands, true, builder);
        builder.append(" ");
        convertThree(Math.abs(number%1_000), fem, builder);
        return builder.toString().trim();
    }

    /**
     * @param number three digits to be named
     * @param rankName rank of this three digit part
     * @param fem true, if number used to count things in feminine form
     * @param builder StringBuilder, for which append will be invoked
     */
    private static void convertThree(int number, String[] rankName, boolean fem, StringBuilder builder){
        if (number == 0) {
            return;
        }
        convertThree(number, fem, builder);
        pickForm(number, rankName, builder);
    }

    /**
     * @param number three digits to be named
     * @param fem true, if number used to count things in feminine form
     * @param builder StringBuilder, for which append will be invoked
     */
    private static void convertThree(int number, boolean fem, StringBuilder builder){
        int twoLast = number%100;
        if (number == 0) return;
        builder.append(hundreds[number/100]).append(" ");
        if (twoLast<20) {
            if (fem && twoLast%10==1){
                builder.append("одна").append(" ");
            } else if (fem && twoLast % 10 == 2) {
                builder.append("две").append(" ");
            } else {
                builder.append(zeroToNineteen[twoLast]).append(" ");
            }
        } else {
            builder.append(tens[twoLast/10]).append(" ");
            if (fem && twoLast%10==1 ){
                builder.append("одна");
            } else if (fem && twoLast%10==2) {
                builder.append("две");
            } else {
                builder.append(zeroToNineteen[twoLast%10]);
            }
        }
    }

    /**
     *
     * @param number tree digit part of a number, determines word endings
     * @param rankName rank of this three digit part
     * @param builder StringBuilder, for which append will be invoked
     */
    private static void pickForm(int number, String[] rankName, StringBuilder builder){
        int form;
        int lastTwo = number % 100;
        int lastDigit = number % 10;
        if ((lastTwo>=11 && lastTwo<=14) || (number!=0 && lastDigit==0)){
            form = 2;
        } else if (lastDigit == 1) {
            form = 0;
        } else if (lastDigit <5){
            form = 1;
        } else {
            form = 2;
        }
        builder.append(" ").append(rankName[form]);
    }

    /**
     *
     * @param d fractional number to be named
     * @return String containing the name of the integer part, the word "whole parts" and the name of the fractional part
     */
    public static String convertDoubleToString(double d ){
        StringBuilder builder = new StringBuilder();
        builder.append(convertIntToString((int) (d), true)).append(" ");
        int lastTwo = ((int) d)%100;
        if (lastTwo%10==1 && !(lastTwo>=11 && lastTwo<=14)){
            builder.append("целая").append(" ");
        } else if ((int) d <5) {
            builder.append("целые").append(" ");
        } else {
            builder.append("целых").append(" ");
        }
        convertFractionalPart(d, builder);
        return builder.toString().trim();
    }

    /**
     *
     * @param d fractional number to be named
     * @param builder StringBuilder, for which append will be invoked
     */
    private static void convertFractionalPart(double d, StringBuilder builder){
        int number = (int) Math.round((d-(int)d)*100);
        int hundred = number%10;
        int ten = number/10;
        if (number==0){
            return;
        } else if (hundred==0) {
            if (ten == 1){
                builder.append("одна").append(" ").append(tenth[0]);
            } else if (ten == 2) {
                builder.append("две").append(" ").append(tenth[1]);
            } else if (ten<5) {
                builder.append(zeroToNineteen[ten]).append(" ").append(tenth[1]);
            } else {
                builder.append(zeroToNineteen[ten]).append(" ").append(tenth[2]);
            }
        }else {
            if (number==1){
                builder.append("одна").append(" ").append(hundredth[0]);
            } else if (number==2) {
                builder.append("две").append(" ").append(hundredth[1]);
            } else if (number<5) {
                builder.append(zeroToNineteen[number]).append(" ").append(hundredth[1]);
            } else if (number<20) {
                builder.append(zeroToNineteen[number]).append(" ").append(hundredth[2]);
            } else {
                builder.append(tens[ten]).append(" ").append(zeroToNineteen[hundred]).append(" ").append(hundredth[2]);
            }
        }
    }

    /**
     *
     * @param days number of days to divide by 7
     * @return String with number of weeks and the word "weeks" with the required ending
     */
    public static String stringDaysToWeek(int days){
        int weeks = days/7;
        if (weeks%10==1) {
            return weeks + " неделя";
        } else if (weeks%10==0) {
            return weeks+" недель";
        } else if (weeks%10<5) {
            return weeks+" недели";
        }else {
            return weeks+" недель";
        }
    }
}
