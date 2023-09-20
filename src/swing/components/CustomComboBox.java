package swing.components;

import cache.CountryCache;

import javax.swing.*;
import java.io.IOException;

public class CustomComboBox extends JComboBox {
    public CustomComboBox() throws IOException {
        if (CountryCache.isInit()) {
            for (String country : CountryCache.getMap().values()) {
                this.addItem(country);
            }
        }
    }
}
