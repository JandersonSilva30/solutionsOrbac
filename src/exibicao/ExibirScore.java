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
	
		
	private String[] colunasTabela = new String[]{ "Regra","Organização","Tipo","Role", "Qtd_Subjects","Activity", "Qtd_Actions","View", "Qtd_Objetcs", "Score" };  
	private DefaultTableModel tab = new DefaultTableModel(null,colunasTabela);
		
	//construtor
	public ExibirScore() throws COrbacException {
				
		this.p =LoadPolicyAll.getInstance().getPolice();	//obtendo as politicas		
		this.v = this.p.GetAbstractConflicts();				//obtendo os conflitos		
					
		this.carregaTabela();								//carregando os dados obtidos		
	}	

	private void carregaTabela() throws COrbacException{
		
		if (!this.v.isEmpty()){    					//se a tabela não estiver vazia
			
			int contadorLoop =0;
			
			for(CAbstractConflict c : this.v){		//percorrendo o Set e preenche a tabela
				contadorLoop++;
								
				tab.addRow(new String[] {  			//extraindo dados da primeira regra
						
						c.GetFirstRule().GetName(),
						c.GetFirstRule().GetOrganization(),
						Score.getInstance().tipoRegra(c.GetFirstRule().GetType()),
						c.GetFirstRule().GetRole(),
						Integer.toString(this.verificaSubject(c.GetFirstRule().GetRole())),
						c.GetFirstRule().GetActivity(),
						Integer.toString(this.verificaAction(c.GetFirstRule().GetActivity())),
						c.GetFirstRule().GetView(),
						Integer.toString(this.verificaObjects(c.GetFirstRule().GetView())),
						Score.getInstance().obterScore(c.GetFirstRule().GetName())											                
		            }); //tabela regra1
				
				
				tab.addRow(new String[] {  			//extraindo dados da segunda regra
						
						c.GetSecondRule().GetName(),
						c.GetSecondRule().GetOrganization(),
						Score.getInstance().tipoRegra(c.GetSecondRule().GetType()),
						c.GetSecondRule().GetRole(),
						Integer.toString(this.verificaSubject(c.GetSecondRule().GetRole())),
						c.GetSecondRule().GetActivity(),
						Integer.toString(this.verificaAction(c.GetSecondRule().GetActivity())),
						c.GetSecondRule().GetView(),
						Integer.toString(this.verificaObjects(c.GetSecondRule().GetView())),
						Score.getInstance().obterScore(c.GetSecondRule().GetName())	
		            }); //tabela regra 2
				
				//esse bloco pode ser retirado depois, foi feito apenas pra acompanhar o loop
				System.err.println("----------------------------------");				
				System.err.println("nome da 1ª regra: "+ c.GetFirstRule().GetName());
				System.err.println("nome da 2ª regra: "+ c.GetSecondRule().GetName());
			
			}//fim do for
			
			System.out.println("RODOU  "+ contadorLoop);
			
			new GerarTela(tab);
			
		}else {										//fim do if			
			
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter as Politicas", 
					"Ops! Algo deu errado", JOptionPane.ERROR_MESSAGE);
		
		}											// fim do else		
	}
	
	
	private int verificaSubject(String a) throws COrbacException{
						
		return new SubjectsAffecteds().getSubEntity(a);
	}
	
	private int verificaAction(String a) throws COrbacException{
		
		return new ActionsAffecteds().getSubEntity(a);
	}
	
	private int verificaObjects(String a) throws COrbacException{
		
		return new ObjectsAffecteds().getSubEntity(a);
	}

}
