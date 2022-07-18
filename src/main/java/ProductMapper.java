import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class ProductMapper {
    public List<Map<String, Object>> mapProducts(@NotNull List<String> codesList, @NotNull Map<String, Map<String, Object>> mappings) {
        return codesList.stream().collect(Collectors.groupingBy(code -> code))
                .entrySet().stream().map(codeToQuantity -> {
                    Map<String, Object> product = mappings.get(codeToQuantity.getKey());
                    Map<String, Object> productMap = new LinkedHashMap<>(product);
                    productMap.put("quantity", codeToQuantity.getValue().size());
                    return productMap;
                }).sorted(Comparator.comparingInt(it -> (Integer)it.get("quantity")))
                .collect(Collectors.toList());
    }
}

