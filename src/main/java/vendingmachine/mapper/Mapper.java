package vendingmachine.mapper;

import vendingmachine.domain.Goods;
import vendingmachine.dto.GoodsDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    public static Map<Goods, Integer> goodsDtosToGoods(List<GoodsDto> dtos) {
        Map<Goods, Integer> goods = new HashMap<>();
        for (GoodsDto dto : dtos) {
            goods.put(new Goods(dto.getName(), dto.getPrice()), dto.getAmount());
        }
        return goods;
    }
}
