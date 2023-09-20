package swing.windows;

import swing.components.ButtonGetNumbers;
import swing.components.CustomComboBox;
import swing.components.CustomJTable;
import swing.components.ExportToExcelButton;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainWindow {
    public void createGUI() throws IOException {
        JFrame frame = new JFrame("БЕСПЛАТНЫЕ НОМЕРА");
        ImageIcon img = new ImageIcon("png-transparent-phone-contact-icon-logo-iphone-computer-icons-telephone-call-phone-icon-electronics-text-grass-thumbnail.png");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contents = new JPanel();

        CustomComboBox jComboBox = new CustomComboBox();
        contents.add(jComboBox);
        CustomJTable customJTable = new CustomJTable();
        JLabel infoLabel = new JLabel();
        infoLabel.setForeground(Color.RED);
        ButtonGetNumbers buttonGetNumbers = new ButtonGetNumbers(jComboBox, "Показать данные", customJTable, infoLabel);
        contents.add(buttonGetNumbers);
        contents.add(infoLabel);

        ExportToExcelButton exportToExcelButton = new ExportToExcelButton(jComboBox, infoLabel, customJTable);
        contents.add(exportToExcelButton, infoLabel);

        customJTable.setPreferredSize(new Dimension(1095, 425));
        customJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane pane = new JScrollPane(customJTable);
        pane.setPreferredSize(new Dimension(1100, 450));
        contents.add(pane, BorderLayout.CENTER);
        frame.setContentPane(contents);
        frame.setVisible(true);

        frame.setPreferredSize(new Dimension(1200, 600));

        frame.pack();
        frame.setVisible(true);
    }
}
