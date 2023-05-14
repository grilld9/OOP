import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.maksimenkov.pizzeria.Pizzeria;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

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
    public void testWithJson() throws IOException, ParseException, InterruptedException {
        Object o = new JSONParser().parse(new FileReader(
            "src/main/resources/info.json"));
        JSONObject j = (JSONObject) o;
        Long nBaker = (Long) j.get("nBaker");
        Long nCourier = (Long) j.get("nCourier");
        Long warehouse = (Long) j.get("warehouse");
        List<Integer> trunkCaps = Arrays.asList(1, 2);
        Pizzeria pizzeria = new Pizzeria(nBaker, warehouse, trunkCaps);
        pizzeria.start();
        sleep(50000);
    }
}
