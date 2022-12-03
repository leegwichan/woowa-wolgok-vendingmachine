package vendingmachine.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
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
}
