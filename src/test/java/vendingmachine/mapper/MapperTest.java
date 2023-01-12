package vendingmachine.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vendingmachine.domain.Goods;
import vendingmachine.dto.GoodsDto;
import java.util.List;
import java.util.Map;

public class MapperTest {

    @Test
    void goodsDtosToGoodsTest() {
        List<GoodsDto> dtos = List.of(
                new GoodsDto("콜라", 100, 5),
                new GoodsDto("사이다", 150, 3));

        Map<Goods, Integer> result = Mapper.goodsDtosToGoods(dtos);

        assertThat(result.keySet().size()).isEqualTo(2);
    }

    @Test
    void inputFormToGoodsDtosTest_NormalData() {
        String inputForm = "[콜라,1500,20];[사이다,1000,10]";
        List<GoodsDto> expected = List.of(new GoodsDto("콜라", 1500, 20),
                                        new GoodsDto("사이다", 1000, 10));

        List<GoodsDto> result = Mapper.inputFormToGoodsDtos(inputForm);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Case : {0}")
    @CsvSource(value = {"[콜라,1500,20;사이다,1000,10]@[ERROR] 상품 형식이 올바르지 않습니다.",
            "[,1500,20];[사이다,1000,10]@[ERROR] 상품 이름이 한 글자 이상이어야 합니다.",
            "[콜라,1500,개수];[사이다,1000,10]@[ERROR] 상품 가격과 상품 개수는 숫자이어야 합니다.",
            "[콜라,가격,20];[사이다,1000,10]@[ERROR] 상품 가격과 상품 개수는 숫자이어야 합니다."},
                delimiter = '@')
    void inputFormToGoodsDtosTest_Exception(String inputForm, String exceptionMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Mapper.inputFormToGoodsDtos(inputForm));

        assertThat(exception.getMessage()).isEqualTo(exceptionMessage);
    }
}
