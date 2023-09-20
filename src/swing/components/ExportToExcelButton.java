package swing.components;

import cache.CountryCache;
import cache.NumberCache;
import model.Number;
import web.WebConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportToExcelButton extends JButton {
    Logger logger = Logger.getLogger(ExportToExcelButton.class.getName());

    public ExportToExcelButton(JComboBox jComboBox, JLabel jLabel, CustomJTable customJTable) {
        ActionListener actionListener = new ExportToExcelButton.ActionActionButton();
        this.jComboBox = jComboBox;
        this.addActionListener(actionListener);
        ImageIcon icon = new ImageIcon("png-transparent-excel-logo-logos-logos-and-brands-icon.png");
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        this.setIcon(icon);
        this.jLabel = jLabel;
        this.customJTable = customJTable;
    }

    private JComboBox jComboBox;

    private JLabel jLabel;
    private CustomJTable customJTable;

    public class ActionActionButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (WebConnect.pingCountries()) {
                    logger.log(Level.INFO, "\u001B[32m" + "Выгрузка данных в excel..." + "\u001B[0m");
                    if (CountryCache.isInit() && NumberCache.isInit()) {
                        if (!customJTable.isEmpty()) {
                            jLabel.setText("");
                            List<Number> numbers = NumberCache.getNumbersByCountryId(CountryCache.getCountryIdByCountryText(jComboBox.getSelectedItem().toString()));
                            String[] columnNames = {"number", "country", "updatedAt", "dataHumans", "fullNumber", "countryText", "maxDate", "status"};
                            PrintWriter writer = null;
                            try {
                                System.out.println(System.currentTimeMillis());
                                writer = new PrintWriter("sample-" + System.currentTimeMillis() + ".csv");
                            } catch (FileNotFoundException ex) {
                                logger.log(Level.WARNING, "Не удалось сформировать файл!");
                                jLabel.setText("Не удалось сформировать файл!");
                                throw new FileNotFoundException();
                            }
                            writer.println(Arrays.toString(columnNames));

                            for (Number number : numbers) {
                                writer.println(number.toString());
                            }
                            writer.close();
                            logger.log(Level.INFO, "\u001B[32m" + "Выгрузка данных в excel успешно завершена" + "\u001B[0m");
                            jLabel.setForeground(Color.GREEN);
                            jLabel.setText("Файл сохранен!");
                        } else {
                            jLabel.setText("Выберите страну и нажмите на кнопку \"Показать данные\"");
                            logger.log(Level.WARNING, "Action at button \"ExportToExcelButton\"");
                        }
                    } else {
                        jLabel.setText("Подождите, данные загружаются...");
                        jLabel.setForeground(Color.RED);
                        logger.log(Level.WARNING, "Action at button \"ExportToExcelButton\"");
                        throw new RuntimeException();
                    }
                }
            } catch (FileNotFoundException ex) {
                logger.log(Level.WARNING, "Файл не сформировался!");
                jLabel.setForeground(Color.RED);
                jLabel.setText("Файл не сформировался!");
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
