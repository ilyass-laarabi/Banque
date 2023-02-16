package predentation.palet;

import predentation.model.Client;
import predentation.model.Compte;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel {
    String [] columnsNames;
    Object[][] data;
public TableModel(String... strings){
    initColumnNames(strings);
}
private void initColumnNames(String... colsNames){
    columnsNames =new String[colsNames.length];
    for (int i = 0; i <colsNames.length ; i++) {
        columnsNames[i]=colsNames[i];
    }
}
public void initClientdata(List<Client> clients){
    int i=0;
    for (Client client:clients
         ) {
        data[i][0]=client.getId();
        data[i][1]=client.getNom();
        data[i][2]=client.getPrenom();
        data[i][3]=client.getEmail();
        data[i][4]=client.getLogin();
        data[i][5]=client.getMotDePasse();
        data[i][6]=client.getSexe();data[i][7]=client.getTel();
        i++;

    }
this.fireTableDataChanged();
}
public void initComptesdata(List<Compte> comptes){
    int i=0;
    for (Compte compte:comptes
    ) {
        data[i][0]=compte.getSolde();
        data[i][1]=compte.getDateCreation();
        data[i][2]=compte.getNumeroCompte();
        data[i][3]=compte.getProprietaire();

        i++;

    }
    this.fireTableDataChanged();
}

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnsNames
                .length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnsNames[column];
    }
}
