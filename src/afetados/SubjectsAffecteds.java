package afetados;

import java.util.HashSet;

import orbac.exception.COrbacException;

public class SubjectsAffecteds extends AbstractAffecteds {
	
	private static final SubjectsAffecteds INSTANCE = new SubjectsAffecteds();
	
	@Override
	public int getSubEntity(String s) throws COrbacException {
		// TODO Auto-generated method stub
		
		int cont =   this.GetValue(s);
		
		//System.out.println(s+ "  possui " + cont+ "  subjetcs");
		
		HashSet<String> res = p.GetSubRoles(s);
		
		//System.out.println(s +"  possui " + res.size()+ "  subrules");
		
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
		
		if(p.IsRole(s)){
			
			return "Role";
			
		}else{
			return "Subject";
		}
	}
	

	@Override
	protected int GetValue(String nameRole) throws COrbacException {
				
				
		if(p.IsRole(nameRole)){ 			//verifica se é uma Role, se for, obtem o numero de subjects
			
			return p.GetSubjectsForRole(nameRole).size();
			
		}else{								//caso contrario, é um objeto e ja entra na conta
			
			return 1;
		}			
	}
	
	public static SubjectsAffecteds getInstance() {
		return INSTANCE;
	}

}
