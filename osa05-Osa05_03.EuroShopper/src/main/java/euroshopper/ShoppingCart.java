package euroshopper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    private Map<Item, Long> items = new HashMap<>();

    public void addToCart(Item item) {
        items.put(item, items.getOrDefault(item, 0L) + 1);
    }

    public double getSum() {
        double sum = 0;
        for (Item item : items.keySet()) {
            sum += items.get(item);
        }
        return sum;
    }

}
