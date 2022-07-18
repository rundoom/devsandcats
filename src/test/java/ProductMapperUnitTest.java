import org.junit.jupiter.api.Assertions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductMapperUnitTest {
    Map<String, Map<String, Object>> codesMappings;
    ProductMapper productMapper = new ProductMapper();


    @org.junit.jupiter.api.BeforeEach
    void prepareData() {
        prepareCodesMappings();
    }

    private void prepareCodesMappings() {
        LinkedHashMap<String, Object> product1 = new LinkedHashMap<>();
        product1.put("version", 1);
        product1.put("edition", "X");

        LinkedHashMap<String, Object> product2 = new LinkedHashMap<>();
        product2.put("version", 2);
        product2.put("edition", "Z");

        LinkedHashMap<String, Object> product3 = new LinkedHashMap<>();
        product3.put("version", 1);

        codesMappings = Map.of(
                "CVCD", product1,
                "SDFD", product2,
                "DDDF", product3
        );
    }

    private List<LinkedHashMap<String, Object>> prepareExpectedMappings() {
        LinkedHashMap<String, Object> product1 = new LinkedHashMap<>();
        product1.put("version", 1);
        product1.put("edition", "X");
        product1.put("quantity", 1);

        LinkedHashMap<String, Object> product2 = new LinkedHashMap<>();
        product2.put("version", 1);
        product2.put("quantity", 1);

        LinkedHashMap<String, Object> product3 = new LinkedHashMap<>();
        product3.put("version", 2);
        product3.put("edition", "Z");
        product3.put("quantity", 2);

        return List.of(product1, product2, product3);
    }

    @org.junit.jupiter.api.Test
    void testProductMappings() {
        List<String> productList = List.of("CVCD", "SDFD", "DDDF", "SDFD");

        List<Map<String, Object>> mappedProducts = productMapper.mapProducts(productList, codesMappings);

        Assertions.assertIterableEquals(mappedProducts, prepareExpectedMappings());
    }
}
