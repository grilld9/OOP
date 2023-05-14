import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.maksimenkov.pizzeria.Pizzeria;
import ru.nsu.fit.maksimenkov.pizzeria.PizzeriaConfig;

import java.util.Arrays;
import java.util.List;



import static java.lang.Thread.sleep;

public class LibraryTest {

    @Test
    public void test() throws InterruptedException {
        Long nBaker = 3L;
        Long warehouseSize = 2L;
        List<Integer> trunkCaps = Arrays.asList(1, 2);
        Pizzeria pizzeria = new Pizzeria(nBaker, warehouseSize, trunkCaps);
        pizzeria.start();
        sleep(50000);
    }

    @Test
    public void testWithJson() throws IOException, InterruptedException {
        String json = "{ \"nBaker\" : 3 , \"nCourier\" : 2 , \"warehouse\" : 2 , \"trunkCaps\" : [1, 2] }";
        ObjectMapper objectMapper = new ObjectMapper();
        PizzeriaConfig pizzeriaConfig = objectMapper.readValue(json, PizzeriaConfig.class);
        Pizzeria pizzeria = new Pizzeria(pizzeriaConfig);
        pizzeria.start();
        sleep(50000);
    }
}
