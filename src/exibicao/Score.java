package exibicao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import afetados.*;
import metados.LoadPolicyAll;
import orbac.exception.COrbacException;
import orbac.securityRules.CAbstractRule;

public final class Score {	
	
	private Map<String, CAbstractRule> res ;	
	private static final Score INSTANCE = new Score();
	private Map<String, Integer> valores;	
	
	int role, activity, view;
	int total;
	
	private Score(){
		super();
		
		valores = new HashMap<String,Integer>() ;
				
		try {
			
			this.res = LoadPolicyAll.getInstance().getPolice().GetAllAbstractRules(); //obtendo todas as regras abstratas
		
		} catch (COrbacException e) {
			
			e.printStackTrace();
		}	
				
		this.preencheValores();
	}
	
	
	public static Score getInstance() {
		return INSTANCE;
	}

	public Map<String, Integer> getValores() {
		return valores;
	}

	private void preencheValores(){
		
		Set<String> s = this.res.keySet();     // obtem as chaves do MAP
		
		System.out.println("valor de objetos encontrados: "+ res.size());   //apenas informa o numeor de objetos no MAP
		
		for(String regra : s){
				
			System.out.println("String obtida: "+ regra);  // confirmando o nome da regra
			try {
				
				valores.put(regra, this.calculaScore(res.get(regra)));				
				
			} catch (COrbacException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
		
		this.imprime();
	}
	
	private int calculaScore(CAbstractRule c) throws COrbacException {
		
		role 		= new SubjectsAffecteds().getSubEntity(c.GetRole());
		activity	= new ActionsAffecteds().getSubEntity(c.GetActivity());
		view		= new ObjectsAffecteds().getSubEntity(c.GetView());
		
		total = role * activity *view; 
		
		return total;
				
	}
	
	private void imprime(){
		
		Set<String> g = this.valores.keySet();
		
		for(String a : g){
			System.out.println(a+ " - "+valores.get(a) );
		}
		
	}
}
