package exibicao;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ListaCellRendererComponent extends DefaultTableCellRenderer {

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int numColum;	
	int linha =1;
		
	public ListaCellRendererComponent(int numColum) {
		super();
		this.numColum = numColum *2 ;

	}	

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus,int row, int column ) {
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							
		if(linha <= this.numColum){
				setBackground(Color.YELLOW);
				linha=linha+1;								
		}else{
			if(linha >= this.numColum && linha <=(this.numColum*2)){
			setBackground(null);
			linha=linha+1;
			}else{
				setBackground(Color.YELLOW);
				linha =2;
			}		
		}		
		
		/*if(isSelected){
			setBackground(Color.GREEN);
		}*/
		
		return this;
	}
}
