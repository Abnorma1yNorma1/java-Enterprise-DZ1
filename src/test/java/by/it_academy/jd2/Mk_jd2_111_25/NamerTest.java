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
    public void convertDoubleToStringShouldCombineEverythingTogether(){
        Assertions.assertEquals("одна целая две десятые", Namer.convertDoubleToString(1.2));
    }
}
