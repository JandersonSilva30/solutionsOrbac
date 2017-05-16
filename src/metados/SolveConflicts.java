package metados;

import exibicao.ExibirScore;
import exibicao.Score;
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
			this.getHighScore(c);			
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
	}
	
	private void getHighScore(CAbstractConflict c) throws COrbacException{
		
		String name1 = c.GetFirstRule().GetName();
		String name2 = c.GetSecondRule().GetName();
		
		int regra1 = Integer.parseInt(Score.getInstance().obterScore(c.GetFirstRule().GetName()));
		int regra2 = Integer.parseInt(Score.getInstance().obterScore(c.GetSecondRule().GetName()));
		
		if(regra1 == regra2){
			
			System.out.println("O conflito entre "+name1 +" >> "+name2+ " não pode ser resolvido por terem os Scores iguais");
			
		}else{			
			if(regra1 > regra2){
				
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
