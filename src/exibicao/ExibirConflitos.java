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
		                this.tipoRegra(c.GetFirstRule().GetType())	                
		            }); //tabela regra1
				
				//extraindo dados da segunda regra
				tab.addRow(new String[] {  
						c.GetSecondRule().GetName(),
						c.GetSecondRule().GetOrganization(),
		                c.GetSecondRule().GetRole(),
		                c.GetSecondRule().GetActivity(),
		                c.GetSecondRule().GetView(),
		                c.GetSecondRule().GetContext(),
		                this.tipoRegra(c.GetSecondRule().GetType())     
		            }); //tabela regra 2
			}//fim do for			
		}//fim do if
		
		new GerarTela(tab);
		
	}
	
		
	//regra auxiliar para extrair o nome da regra atraves do codigo
	private String tipoRegra(int regra){		

		String s="";

		switch(regra){

		case 0 : s = "permissão";
		break;

		case 1 : s = "proibição";
		break;

		case 2 : s = "obrigacao";
		break;

		default: s = "Invalid type";
		break;		

		}		
		return s;

	}	
	

}
