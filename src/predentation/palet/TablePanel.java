package predentation.palet;



import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TablePanel extends JPanel {
    TableModel tableModel;
    JTable table;
    JScrollPane jScrollPane;
    private JTableHeader tableHeader;
    private void inittable(){
        tableModel=new TableModel("id","nom","prenom","login","pass","cin","sexe","email");
        //tableModel.initClientdata(new ClientDao().findAll());
        table=new JTable(tableModel);
        table.setFont(new Font("optimal",Font.BOLD,17));
        table
                .setBackground(new Color(45,83,187));
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHeader=table.getTableHeader();
        tableHeader.setFont(new Font("optimal",Font.BOLD,21));
        tableHeader.setForeground(new Color(182,104,55));
        ((DefaultTableCellRenderer)tableHeader
                .getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
       // JtABLEUTT.setCellsA(table,SwingConstants.CENTER
       // )
        jScrollPane =new JScrollPane(table);
    }public TablePanel(){
    inittable();
    setLayout(new GridLayout(1,1));
    add(jScrollPane);
    }
}

