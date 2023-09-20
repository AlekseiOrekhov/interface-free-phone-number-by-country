package swing.components;

import model.Number;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CustomJTable extends JTable {
    private DefaultTableModel model;
    private boolean isEmpty;

    public CustomJTable() {

        // Column Names
        String[] columnNames = {"number", "country", "updatedAt", "dataHumans", "fullNumber", "countryText", "maxDate", "status"};

        model = (DefaultTableModel) this.getModel();
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }
        isEmpty = true;
    }

    public void addNewData(List<Number> numbers) {
        model = (DefaultTableModel) this.getModel();
        model.setRowCount(0);
        int countRow = 0;
        for (Number number : numbers) {
            model.addRow(new Object[0]);
            model.setValueAt(number.getNumber(), countRow, 0);
            model.setValueAt(number.getCountry(), countRow, 1);
            model.setValueAt(number.getUpdatedAat(), countRow, 2);
            model.setValueAt(number.getDataHumans(), countRow, 3);
            model.setValueAt(number.getFullNumber(), countRow, 4);
            model.setValueAt(number.getCountryText(), countRow, 5);
            model.setValueAt(number.getMaxDate(), countRow, 6);
            model.setValueAt(number.getStatus(), countRow, 7);
            countRow++;
        }
        isEmpty = false;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
