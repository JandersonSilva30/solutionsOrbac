package exibicao;

import java.util.Set;

import javax.swing.table.DefaultTableModel;

import metados.LoadPolicyAll;
import orbac.AbstractOrbacPolicy;
import orbac.conflict.CAbstractConflict;
import orbac.exception.COrbacException;

public class ExibirConflitos {	
	
	private AbstractOrbacPolicy police;
		
	private Set<CAbstractConflict> v;	
	private String[] colunasTabela = new String[]{ "Regra","Organização", "Role", "Activity", "View", "Context", "Tipo" };  
	private DefaultTableModel tab = new DefaultTableModel(null,colunasTabela);
		
	public ExibirConflitos() throws COrbacException {
		this.police =LoadPolicyAll.getInstance().getPolice();
		this.v = this.police.GetAbstractConflicts();	
		this.carregaTabela();
		
	}	

	private void carregaTabela(){
		
		if (!this.v.isEmpty() ){			
			//percorrendo o Set e preenchendo a tabela
			for(CAbstractConflict c : this.v){
				//extraindo dados da primeira regra
				tab.addRow(new String[] {  
						c.GetFirstRule().GetName(),
						c.GetFirstRule().GetOrganization(),
		                c.GetFirstRule().GetRole(),
		                c.GetFirstRule().GetActivity(),
		                c.GetFirstRule().GetView(),
		                c.GetFirstRule().GetContext(),
		                Score.getInstance().tipoRegra(c.GetFirstRule().GetType())		                	                
		            }); //tabela regra1
				
				//extraindo dados da segunda regra
				tab.addRow(new String[] {  
						c.GetSecondRule().GetName(),
						c.GetSecondRule().GetOrganization(),
		                c.GetSecondRule().GetRole(),
		                c.GetSecondRule().GetActivity(),
		                c.GetSecondRule().GetView(),
		                c.GetSecondRule().GetContext(),
		                Score.getInstance().tipoRegra(c.GetSecondRule().GetType())
		            }); //tabela regra 2
			}//fim do for			
		}//fim do if
		
		new GerarTela(tab);
		
	}
}
