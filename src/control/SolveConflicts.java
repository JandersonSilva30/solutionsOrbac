package control;

import java.io.IOException;

import javax.swing.JOptionPane;

import exibicao.ExibirScore;
import exibicao.Score;
import metados.LoadPolicyAll;
import orbac.AbstractOrbacPolicy;
import orbac.conflict.CAbstractConflict;
import orbac.exception.COrbacException;
import orbac.securityRules.CConcretePermission;
import orbac.securityRules.CRulePriority;

public class SolveConflicts {
	
	AbstractOrbacPolicy p ;

	public SolveConflicts() {
		super();
		this.p = LoadPolicyAll.getInstance().getPolice();
		
		try {
			
			this.solveConfli();
			
		} catch (COrbacException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void solveConfli() throws COrbacException{
		
				
		for(CAbstractConflict c : p.GetAbstractConflicts()){
			this.appyPriority(c);			
		}
		
				
		System.out.println("regras ja aplicadas");
		
		
		System.out.println("verificando prioridades");
		
		for(CRulePriority cRulePriority : p.GetRulesPriorities()){
			System.out.println(cRulePriority.GetFirstRule()+" >> "+cRulePriority.GetSecondRule());
		}		
		
		System.out.println("verificando permissoes");
		
		for(CConcretePermission permission : p.GetConcretePermissions()){
			System.out.println(
				"["+p.IsPermited(permission)+"]"+
				permission.GetName()+" / "+permission.GetSubject()+" / "+permission.GetAction()+" / "+
				permission.GetObject()+" >>>> "+permission.toString());
		}
		
		new ExibirScore();
		
		//descomentar na versao final
		//this.savePolicy();		
		
	}
	
	private void savePolicy(){
		
		int resp = JOptionPane.showConfirmDialog(null, "Deseja Salva as mudanças?");
		
		if (resp == JOptionPane.YES_OPTION) {
			
			try {				
				p.WritePolicyFile(LoadPolicyAll.getInstance().getPath_police(), null);
				System.err.println("REGRAS SALVAS COM SUCESSO!!");
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else{
			System.err.println("OPERAÇÃO ABORTADADA");
		}
		
		
		
	}
	
	
	private void appyPriority(CAbstractConflict c) throws COrbacException{
		
		String name1 = c.GetFirstRule().GetName();
		String name2 = c.GetSecondRule().GetName();
		
		int scoreRule1 = Integer.parseInt(Score.getInstance().obterScore(name1));
		int scoreRule2 = Integer.parseInt(Score.getInstance().obterScore(name2));
		
		if(scoreRule1 == scoreRule2){
			
			System.out.println("O conflito entre "+name1 +" >> "+name2+ " não pode ser resolvido por terem os Scores iguais");
			
		}else{			
			if(scoreRule1 > scoreRule2){
				
				p.SetRule1AboveRule2(c.GetFirstRule().GetName(),
						 			 c.GetSecondRule().GetName(),
						 			 c.GetFirstRule().GetOrganization(),
						 			 c.GetSecondRule().GetOrganization());				
			}else{
				
				p.SetRule1AboveRule2(c.GetSecondRule().GetName(),
						 			 c.GetFirstRule().GetName(),
						 			 c.GetSecondRule().GetOrganization(),
						 			 c.GetFirstRule().GetOrganization());				
			}			
		}		
	}
}
