package by.it_academy.jd2.Mk_jd2_111_25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NamerTest {

    @Test
    public void convertIntToStringShouldCombineEverythingTogether(){

        Assertions.assertEquals("двадцать одна тысяча", Namer.convertIntToString(21_000, false));
        Assertions.assertEquals("две", Namer.convertIntToString(2, true));
    }

    @Test
    public void convertThreeShouldNameThreeDigits(){
        String[] thousands = {
                "тысяча", "тысячи", "тысяч"
        };
        StringBuilder builder = new StringBuilder();
        Namer.convertThree(123, thousands, true, builder);
        Assertions.assertEquals("сто двадцать три тысячи", builder.toString());

        builder.delete(0, builder.length());
        Namer.convertThree(21, false, builder);
        Assertions.assertEquals(" двадцать один", builder.toString());
    }

    @Test
    public void pickFormShouldPicSecondForm(){
        String[] thousands = {
                "тысяча", "тысячи", "тысяч"
        };
        StringBuilder builder = new StringBuilder();
        Namer.pickForm(12, thousands, builder);
        Assertions.assertEquals(" тысяч", builder.toString());
    }

    @Test
    public void convertDoubleToStringShouldCombineEverythingTogether(){
        Assertions.assertEquals("одна целая две десятые", Namer.convertDoubleToString(1.2));
    }

    @Test
    public void convertFractionalPartShouldProvideFraction(){
        StringBuilder builder = new StringBuilder();
        Namer.convertFractionalPart(0.02, builder);
        Assertions.assertEquals("две сотые", builder.toString());
    }

    @Test
    public void stringDaysToWeekShouldProvideWeeks(){
        Assertions.assertEquals("2 недели", Namer.stringDaysToWeek(15));
    }
}
