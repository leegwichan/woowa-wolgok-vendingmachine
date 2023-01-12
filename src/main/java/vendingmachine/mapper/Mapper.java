package vendingmachine.mapper;

import vendingmachine.domain.Goods;
import vendingmachine.dto.GoodsDto;
import vendingmachine.exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    private static final String GOODS_REGEX = ";";
    private static final String INFO_REGEX = ",";
    private static final String GOODS_PREFIX = "[";
    private static final String GOODS_SUFFIX = "]";

    public static Map<Goods, Integer> goodsDtosToGoods(List<GoodsDto> dtos) {
        Map<Goods, Integer> goods = new HashMap<>();
        for (GoodsDto dto : dtos) {
            goods.put(new Goods(dto.getName(), dto.getPrice()), dto.getAmount());
        }
        return goods;
    }

    public static List<GoodsDto> inputFormToGoodsDtos(String input) {
        String[] goodsStrings = input.split(GOODS_REGEX, -1);

        List<GoodsDto> goodsDtos = new ArrayList<>();
        for (String goodsString : goodsStrings) {
            goodsDtos.add(goodsFormToGoodsDto(goodsString));
        }
        return goodsDtos;
    }

    private static GoodsDto goodsFormToGoodsDto(String goodsString) {
        // goodsString 형식 : [콜라,1500,20]

        String[] goodsInfo = goodsString
                .substring(1, goodsString.length()-1)
                .split(INFO_REGEX, -1);

        validateGoodsStringForm(goodsString);
        validateGoodsInfoForm(goodsInfo);

        return new GoodsDto(goodsInfo[0], Integer.parseInt(goodsInfo[1]), Integer.parseInt(goodsInfo[2]));
    }

    private static void validateGoodsStringForm(String goodsString) {
        if (!goodsString.startsWith(GOODS_PREFIX) || !goodsString.endsWith(GOODS_SUFFIX)) {
            throw new IllegalArgumentException(ExceptionMessage.GOODS_FORM_INCORRECT.getMessage());
        }
    }

    private static void validateGoodsInfoForm(String[] goodsInfo) {
        if (goodsInfo[0].length() < 1) {
            throw new IllegalArgumentException(ExceptionMessage.GOODS_NAME_INCORRECT.getMessage());
        }

        try {
            Integer.parseInt(goodsInfo[1]);
            Integer.parseInt(goodsInfo[2]);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ExceptionMessage.GOODS_PRICE_COUNT_INCORRECT.getMessage());
        }
    }
}
