package afetados;

import java.util.HashSet;

import orbac.exception.COrbacException;

public class ActionsAffecteds extends AbstractAffecteds {
	
	private static final ActionsAffecteds INSTANCE = new ActionsAffecteds();
	
	@Override
	public int getSubEntity(String s) throws COrbacException {
		// TODO Auto-generated method stub
		
		int cont =   this.GetValue(s);
		
		//System.out.println(s+ "  possui " + cont+ "  Actions");
		
		HashSet<String> res = this.p.GetSubActivities(s);
		
		//System.out.println(s +"  possui " + res.size()+ "  subSubActivities");
		
		this.aux += cont;
		
		if(res.size() > 0 ){
						
			for(String a : res){
				//System.out.println(a);
				this.getSubEntity(a);								
			}
			
		}else{
			
			return this.aux;
		}
		
		return this.aux;
	}
	
	
	@Override
	public String getTypeEntity(String s) throws COrbacException {
		
		if(p.IsActivity(s)){
			
			return "Activity";
			
		}else{
			
			return "Action";
						
		}		
	}

	@Override
	protected int GetValue(String nameActivity) throws COrbacException {
					
		if(p.IsActivity(nameActivity)){				//verifica se é uma atividade, se for, obtem o numero de acoes
			
			return p.GetActionsForActivity(nameActivity).size();
			
		}else{										//caso contrario, é uma ação, e ja entra na conta
			
			return 1;			
		}			
	}
	
	public static ActionsAffecteds getInstance() {
		return INSTANCE;
	}
	

	
}
