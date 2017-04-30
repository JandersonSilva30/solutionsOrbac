package exibicao;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GerarTela {
	
	DefaultTableModel tabela;	
	
	public GerarTela(DefaultTableModel tab) {		
		this.tabela = tab;		
		this.exibirTela();				
	}
	
	private void exibirTela(){
		JTable table = new JTable();
		table.setModel(this.tabela);
		
		//table.setDefaultRenderer(Object.class, new ListaCellRendererComponent(tabela.getColumnCount()));

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1200,700);
		frame.setVisible(true);		
	}	
}
