/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurseconsole1cserver;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
    public class serversModel extends AbstractTableModel{
    
    private String[] columnNames = {"Версия 1С", "Компьютер", "Порт"};
    private Vector data = new Vector();
    
    public serversModel(Vector serversList){
        data.clear();
        int sizeServersList = serversList.size();
        for (int i = 0; i < sizeServersList; i++) {
            addRowTable((Object[])serversList.get(i));
        }
    }
    
    public serversModel(){
        
    }
    
    public void addRowTable(Object[] rowServer){
        data.add(rowServer);
    }
    
    public void delleteRowTable(int rowIndex){
        data.remove(rowIndex);
    }
    
    public Vector getTableData(){
        return data;
    }
    
    @Override
    public int getRowCount() {
        return data.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Object[] rowData = (Object[])(data.get(i));
        return rowData[i1]; //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
}