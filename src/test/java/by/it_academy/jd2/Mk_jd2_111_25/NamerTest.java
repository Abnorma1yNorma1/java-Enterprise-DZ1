package by.it_academy.jd2.Mk_jd2_111_25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NamerTest {

    @Test
    public void convertIntToString_ShouldCombineEverythingTogether(){

        Assertions.assertEquals("двадцать одна тысяча", Namer.convertIntToString(21_000, false));
        Assertions.assertEquals("две", Namer.convertIntToString(2, true));
    }

    @Test
    public void ConvertIntToString_ShouldUseNegative(){
        Assertions.assertEquals("минус двадцать один", Namer.convertIntToString(-21, false) );
    }

    @Test
    public void ConvertIntToString_ShouldProvideZero(){
        Assertions.assertEquals("ноль", Namer.convertIntToString(0, false) );
    }

    @Test
    public void convertThree_ShouldNameThreeDigits(){
        StringBuilder builder = new StringBuilder();
        Namer.convertThree(21, false, builder);
        Assertions.assertEquals("двадцать один ", builder.toString());
    }

    @Test
    public void convertThree_ShouldUseLessThenTwenty(){
        StringBuilder builder = new StringBuilder();
        Namer.convertThree(112, false, builder);
        Assertions.assertEquals("сто двенадцать ", builder.toString());
    }

    @Test
    public void convertThree_ShouldUseFeminineTwoAtTheEnd(){
        StringBuilder builder = new StringBuilder();
        Namer.convertThree(122, true, builder);
        Assertions.assertEquals("сто двадцать две ", builder.toString());
    }

    @Test
    public void convertThree_CanBeOverloadedWithRank() {
        String[] thousands = {
                "тысяча", "тысячи", "тысяч"
        };
        StringBuilder builder = new StringBuilder();
        Namer.convertThree(123, thousands, true, builder);
        Assertions.assertEquals("сто двадцать три тысячи ", builder.toString());
    }

    @Test
    public void pickForm_ShouldPicForm2(){
        String[] thousands = {
                "тысяча", "тысячи", "тысяч"
        };
        StringBuilder builder = new StringBuilder();
        Namer.pickForm(12, thousands, builder);
        Assertions.assertEquals("тысяч ", builder.toString());
    }

    @Test
    public void pickForm_ShouldPicForm2NonExceptionalCase(){
        String[] thousands = {
                "тысяча", "тысячи", "тысяч"
        };
        StringBuilder builder = new StringBuilder();
        Namer.pickForm(125, thousands, builder);
        Assertions.assertEquals("тысяч ", builder.toString());
    }

    @Test
    public void convertDoubleToString_ShouldCombineEverythingTogether(){
        Assertions.assertEquals("одна целая две десятые", Namer.convertDoubleToString(1.2));
    }

    @Test
    public void convertDoubleToString_ShouldNameNumbersBelowFive(){
        Assertions.assertEquals("четыре целые", Namer.convertDoubleToString(4));
    }

    @Test
    public void convertDoubleToString_ShouldNameNumbersBiggerTenFourteenWithoutZeroAtTheEnd(){
        Assertions.assertEquals("двадцать целых", Namer.convertDoubleToString(20));
    }

    @Test
    public void convertFractionalPart_ShouldProvideFraction(){
        StringBuilder builder = new StringBuilder();
        Namer.convertFractionalPart(0.02, builder);
        Assertions.assertEquals("две сотые", builder.toString());
    }

    @Test
    public void convertFractionalPart_ShouldProvideFeminineNumberOne(){
        StringBuilder builder = new StringBuilder();
        Namer.convertFractionalPart(0.1, builder);
        Assertions.assertEquals("одна десятая", builder.toString());
    }

    @Test
    public void convertFractionalPart_ShouldProvideLessThenFiveHundredth(){
        StringBuilder builder = new StringBuilder();
        Namer.convertFractionalPart(0.04, builder);
        Assertions.assertEquals("четыре сотые", builder.toString());
    }

    @Test
    public void convertFractionalPart_ShouldProvideLessThenFiveTenth(){
        StringBuilder builder = new StringBuilder();
        Namer.convertFractionalPart(0.4, builder);
        Assertions.assertEquals("четыре десятые", builder.toString());
    }

    @Test
    public void convertFractionalPart_ShouldProvideTensPlusHundredths(){
        StringBuilder builder = new StringBuilder();
        Namer.convertFractionalPart(0.25, builder);
        Assertions.assertEquals("двадцать пять сотых", builder.toString());
    }

    @Test
    public void convertFractionalPart_ShouldProvideMoreThenFiveTenths(){
        StringBuilder builder = new StringBuilder();
        Namer.convertFractionalPart(0.6, builder);
        Assertions.assertEquals("шесть десятых", builder.toString());
    }

    @Test
    public void convertFractionalPart_ShouldProvideOneHundredths(){
        StringBuilder builder = new StringBuilder();
        Namer.convertFractionalPart(0.01, builder);
        Assertions.assertEquals("одна сотая", builder.toString());
    }

    @Test
    public void convertFractionalPart_ShouldProvideMoreThenTwentyHundredths(){
        StringBuilder builder = new StringBuilder();
        Namer.convertFractionalPart(0.25, builder);
        Assertions.assertEquals("двадцать пять сотых", builder.toString());
    }

    @Test
    public void stringDaysToWeek_ShouldProvideWeeks(){
        Assertions.assertEquals("2 недели", Namer.stringDaysToWeek(15));
    }

    @Test
    public void stringDaysToWeek_ShouldProvideOneWeek(){
        Assertions.assertEquals("1 неделя", Namer.stringDaysToWeek(9));
    }

    @Test
    public void stringDaysToWeek_ShouldProvideWeeksEndingInZero(){
        Assertions.assertEquals("10 недель", Namer.stringDaysToWeek(70));
    }

    @Test
    public void stringDaysToWeek_ShouldProvideBigAmountOfWeeks(){
        Assertions.assertEquals("95 недель", Namer.stringDaysToWeek(7*95));
    }
}
