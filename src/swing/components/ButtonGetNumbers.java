package swing.components;

import cache.CountryCache;
import cache.NumberCache;
import web.WebConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ButtonGetNumbers extends JButton {
    private static final Logger logger = Logger.getLogger(ButtonGetNumbers.class.getName());

    public ButtonGetNumbers(JComboBox jComboBox, String text, CustomJTable customJTable, JLabel jLabel) {
        ActionListener actionListener = new ActionActionButton();
        this.jComboBox = jComboBox;
        this.setText(text);
        this.addActionListener(actionListener);
        this.customJTable = customJTable;
        this.jLabel = jLabel;
    }

    public JComboBox jComboBox;
    public CustomJTable customJTable;
    public JLabel jLabel;

    public class ActionActionButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (WebConnect.pingCountries()) {
                    if (CountryCache.isInit() && NumberCache.isInit()) {
                        jLabel.setForeground(Color.RED);
                        jLabel.setText("");
                        String country = jComboBox.getSelectedItem().toString();
                        logger.log(Level.INFO, "\u001B[32m" + "Показываем данные по стране " + country + "\u001B[0m");
                        customJTable.addNewData(NumberCache.getNumbersByCountryId(CountryCache.getCountryIdByCountryText(country)));
                    } else {
                        logger.log(Level.WARNING, "Кеширование не завершено!");
                        jLabel.setForeground(Color.RED);
                        jLabel.setText("Подождите, данные загружаются...");
                    }
                }
            } catch (RuntimeException ex) {
                logger.log(Level.WARNING, "Нет доступа к сети!");
                jLabel.setForeground(Color.RED);
                jLabel.setText("Нет доступа к сети!");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
