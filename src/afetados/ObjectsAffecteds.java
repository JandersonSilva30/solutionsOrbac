package afetados;

import java.util.HashSet;

import orbac.exception.COrbacException;

public class ObjectsAffecteds extends AbstractAffecteds {

	private static final ObjectsAffecteds INSTANCE = new ObjectsAffecteds();
	
	@Override
	public int getSubEntity(String s) throws COrbacException {
		// TODO Auto-generated method stub
		
		int cont =   this.GetValue(s);
		
		//System.out.println(s+ "  possui " + cont+ "  Objects");
		
		HashSet<String> res = p.GetSubViews(s);
		
		//System.out.println(s +"  possui " + res.size()+ "  subSubView");
		
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
		
		if(p.IsView(s)){
			
			return "View";
					
		}else{
			
			return "Object";
		}
	}

	@Override
	protected int GetValue(String nameView) throws COrbacException {
				
		if(p.IsView(nameView)){				//verifica se é uma View, se for, obtem o numero de objetos
			
			return p.GetObjectsForView(nameView).size();
			
		}else{
			
			return 1;						//caso contrario é um objeto e ja enta na conta
		}
	}

	public static ObjectsAffecteds getInstance() {
		return INSTANCE;
	}

}
