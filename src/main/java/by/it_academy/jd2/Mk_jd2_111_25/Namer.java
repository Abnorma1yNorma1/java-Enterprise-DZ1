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

    private static final String[] hours = {
            "час", "часа" , "часов"
    };

    private static final String[] minutes = {
            "минута", "минуты", "минут"
    };

    private static final String[] seconds = {
            "секунда", "секунды" , "секунд"
    };

    private static final String[] milliseconds = {
            "миллисекунда", "миллисекунды" , "миллисекунд"
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
        convertThree(Math.abs(number/1_000%1_000), thousands, true, builder);
        convertThree(Math.abs(number%1_000), fem, builder);
        return builder.toString().trim();
    }

    /**
     * @param number three digits to be named
     * @param rankName rank of this three digit part
     * @param fem true, if number used to count things in feminine form
     * @param builder StringBuilder, for which append will be invoked
     */
    protected static void convertThree(int number, String[] rankName, boolean fem, StringBuilder builder){
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
    protected static void convertThree(int number, boolean fem, StringBuilder builder){
        if (number == 0) return;
        int twoLast = number%100;
        if (number/100!=0) builder.append(hundreds[number/100]).append(" ");
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
                builder.append("одна").append(" ");
            } else if (fem && twoLast%10==2) {
                builder.append("две").append(" ");
            } else {
                builder.append(zeroToNineteen[twoLast%10]).append(" ");
            }
        }
    }

    /**
     *
     * @param number tree digit part of a number, determines word endings
     * @param rankName rank of this three digit part
     * @param builder StringBuilder, for which append will be invoked
     */
    protected static void pickForm(int number, String[] rankName, StringBuilder builder){
        int form;
        int lastTwo = number % 100;
        int lastDigit = number % 10;
        if ((lastTwo>=11 && lastTwo<=14) || (number!=0 && lastDigit==0) || number==0){
            form = 2;
        } else if (lastDigit == 1) {
            form = 0;
        } else if (lastDigit <5){
            form = 1;
        } else {
            form = 2;
        }
        builder.append(rankName[form]).append(" ");
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
    protected static void convertFractionalPart(double d, StringBuilder builder){
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

    /**
     *
     * @param milliseconds will be turned to time format
     * @param shortFormat defines if resulting format going to be written "HH:mm:ss;SSS" or "H hours m minutes s seconds SS milliseconds"
     * @return time as on a digital clock or in text
     */
    public static String MillisecondsToTimeString( long milliseconds, boolean shortFormat){
        short millis = (short) (milliseconds%1000);
        byte sek = (byte) (milliseconds/1000%60);
        byte min = (byte) (milliseconds/1000/60%60);
        long hour = milliseconds/1000/60/60%60;
        StringBuilder builder = new StringBuilder();
        if (shortFormat){
            writeInShortFormat(hour, min, sek, millis, builder);
        } else {
            writeInLongFormat(hour, min, sek, millis, builder);
        }
        return builder.toString().trim();
    }

    protected static void writeInShortFormat(long hour, byte min, byte sek, short millis, StringBuilder builder){
        if (hour>99){
            builder.append("99:99:99:999");
        } else {
            writeWithZeros((short) (hour%100), 2, builder);
            builder.append(":");
            writeWithZeros(min, 2, builder);
            builder.append(":");
            writeWithZeros(sek, 2, builder);
            builder.append(":");
            writeWithZeros(millis, 3, builder);
        }
    }

    protected static void writeInLongFormat(long hour, byte min, byte sek, short millis, StringBuilder builder){
        builder.append(hour).append(" ");
        pickForm((int) (hour%100), hours, builder);
        builder.append(min).append(" ");
        pickForm(min%100, minutes, builder);
        builder.append(sek).append(" ");
        pickForm(sek%100, seconds, builder);
        builder.append(millis).append(" ");
        pickForm(millis%100, milliseconds, builder);
    }

    /**
     *
     * @param number to be written
     * @param digits amount of digits up to which string shall be filled (max 3)
     * @param builder StringBuilder, for which append will be invoked to write number
     */
    protected static void writeWithZeros (short number, int digits, StringBuilder builder){
        int current = number;
        int [] arr = new int[digits];
        for (int i = arr.length-1; i>=0; i--){
            arr[i]= (current%10);
            current=current/10;
        }
        for (int digit: arr){
            builder.append(digit);
        }
    }
}
