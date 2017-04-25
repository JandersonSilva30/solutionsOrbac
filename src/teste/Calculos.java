package teste;

import java.util.HashSet;

import orbac.AbstractOrbacPolicy;
import orbac.exception.COrbacException;

public class Calculos {
	
	int aux =0;
	
	public int contaSubject(String s, AbstractOrbacPolicy p) throws COrbacException {
						
		return p.GetSubjectsForRole(s).size();			
	}
	
		
	public int verificaSubRoles(String s,AbstractOrbacPolicy p) throws COrbacException{
		
		int cont =   this.contaSubject(s, p);
		
		System.out.println(s+ "  possui " + cont+ "  subjetcs");
		
		HashSet<String> res = p.GetSubRoles(s);
		
		System.out.println(s +"  possui " + res.size()+ "  subrules");
		
		this.aux += cont;
		
		if(res.size() > 0 ){
						
			for(String a : res){
				System.out.println(a);
				this.verificaSubRoles(a, p);								
			}
			
		}else{
			
			return aux;
		}
		
		return aux;			
	}	
}
