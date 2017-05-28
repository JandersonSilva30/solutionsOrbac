package exibicao;

import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import afetados.ActionsAffecteds;
import afetados.ObjectsAffecteds;
import afetados.SubjectsAffecteds;
import metados.LoadPolicyAll;
import orbac.AbstractOrbacPolicy;
import orbac.conflict.CAbstractConflict;
import orbac.exception.COrbacException;

/**
 * Classe responsavel em obter o score de cada regra da organização
 * Primeiramente ele obtem a politica atraves do metodo "LoadPolicy"
 * segundo, obtem todos os conflitos.
 * 
 *  terceiro, obtem da classe "Score" (atraves de um metodo estático) uma collection
 *  com os valores já calculados de todas as regras abstratas e armazenadas em um MAP que 
 *  é gerado no momento da criação da classe SCORE. 
 * @author janderson
 *
 */
public class ExibirScore {	
		
	private Set<CAbstractConflict> v;
	private AbstractOrbacPolicy p ;
	
	//string que forma o cabeçalho da tabela	
	private String[] colunasTabela = new String[]{ "Regra","Organização","Tipo","Role", "Qtd_Subjects",
			"Role/Subject","Activity", "Qtd_Actions","Activity/Action","View", "Qtd_Objetcs", "View/Object","Context","Score" };  
	
	//alocando a string anterior na variavel tab
	private DefaultTableModel tab = new DefaultTableModel(null,colunasTabela);
		
	//construtor
	public ExibirScore() throws COrbacException {
				
		this.p =LoadPolicyAll.getInstance().getPolice();	//obtendo as politicas		
		this.v = this.p.GetAbstractConflicts();				//obtendo os conflitos		
					
		this.carregaTabela();								//carregando os dados obtidos		
	}	

	private void carregaTabela() {
		
		if (!this.v.isEmpty()){    					//se a tabela não estiver vazia
			
			int contadorLoop =0;
			
			for(CAbstractConflict c : this.v){		//percorrendo o Set e preenche a tabela
				contadorLoop++;
								
				try {
					tab.addRow(new String[] {  			//extraindo dados da primeira regra
							
						c.GetFirstRule().GetName(),
						c.GetFirstRule().GetOrganization(),
						Score.getInstance().tipoRegra(c.GetFirstRule().GetType()),
						c.GetFirstRule().GetRole(),
						Integer.toString(new SubjectsAffecteds().getSubEntity(c.GetFirstRule().GetRole())),
						SubjectsAffecteds.getInstance().getTypeEntity(c.GetFirstRule().GetRole()),
						c.GetFirstRule().GetActivity(),
						Integer.toString(new ActionsAffecteds().getSubEntity(c.GetFirstRule().GetActivity())),
						ActionsAffecteds.getInstance().getTypeEntity(c.GetFirstRule().GetActivity()),
						c.GetFirstRule().GetView(),
						Integer.toString(new ObjectsAffecteds().getSubEntity(c.GetFirstRule().GetView())),
						ObjectsAffecteds.getInstance().getTypeEntity(c.GetFirstRule().GetView()),
						c.GetFirstRule().GetContext(),
						Score.getInstance().obterScore(c.GetFirstRule().GetName())											                
					});	 //tab regra1
				
				
					tab.addRow(new String[] {  			//extraindo dados da segunda regra
						
						c.GetSecondRule().GetName(),
						c.GetSecondRule().GetOrganization(),
						Score.getInstance().tipoRegra(c.GetSecondRule().GetType()),
						c.GetSecondRule().GetRole(),
						Integer.toString(new SubjectsAffecteds().getSubEntity(c.GetSecondRule().GetRole())),
						SubjectsAffecteds.getInstance().getTypeEntity(c.GetSecondRule().GetRole()),
						c.GetSecondRule().GetActivity(),
						Integer.toString(new ActionsAffecteds().getSubEntity(c.GetSecondRule().GetActivity())),
						ActionsAffecteds.getInstance().getTypeEntity(c.GetSecondRule().GetActivity()),
						c.GetSecondRule().GetView(),
						Integer.toString(new ObjectsAffecteds().getSubEntity(c.GetSecondRule().GetView())),
						ObjectsAffecteds.getInstance().getTypeEntity(c.GetSecondRule().GetView()),
						c.GetSecondRule().GetContext(),
						Score.getInstance().obterScore(c.GetSecondRule().GetName())	
		            }); //tab regra 2
				
				} catch (COrbacException e) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter os conflitos", 
							"Ops! Algo deu errado", JOptionPane.ERROR_MESSAGE);
					
					e.printStackTrace();
				}
				
				//esse bloco pode ser retirado depois, foi feito apenas pra acompanhar o loop
				System.err.println("----------------------------------");				
				System.err.println("nome da 1ª regra: "+ c.GetFirstRule().GetName());
				System.err.println("nome da 2ª regra: "+ c.GetSecondRule().GetName());
			
			}//fim do for
			
			System.out.println("RODOU  "+ contadorLoop+ "  VEZES");			
			
		}else {										//fim do if			
			
			JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum conflito", 
					"Isso é ótimo", JOptionPane.INFORMATION_MESSAGE);
		
		}											// fim do else	
		
		new GerarTela(tab);
	}
	
//
//	private int verificaSubject(String a) throws COrbacException{
//						
//		return new SubjectsAffecteds().getSubEntity(a);
//	}
//	
//	private int verificaAction(String a) throws COrbacException{
//		
//		return new ActionsAffecteds().getSubEntity(a);
//	}
//	
//	private int verificaObjects(String a) throws COrbacException{
//		
//		return new ObjectsAffecteds().getSubEntity(a);
//	}
	
	

}
