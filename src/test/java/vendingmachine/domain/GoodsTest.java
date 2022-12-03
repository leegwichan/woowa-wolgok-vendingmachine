package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GoodsTest {

    @ParameterizedTest(name = "createGoodsTest_Exception Case {index}")
    @CsvSource(value = {"사이다,213", "콜라,50",",150"})
    void createGoodsTest_Exception(String name, int price) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Goods(name, price);
        });
    }

    @ParameterizedTest(name = "isEqualNameTest Case {index}")
    @CsvSource(value = {"사이다,사이다,true", "사이다,콜라,false"})
    void isEqualNameTest(String name, String comparedName, boolean expected) {
        Goods goods = new Goods(name, 100);

        assertThat(goods.isEqualName(comparedName)).isEqualTo(expected);
    }

}
