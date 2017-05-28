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
		
		System.out.println("Quantidade de regras encontradas: "+ res.size());   //apenas informa o numeor de objetos no MAP
		
		for(String regra : s){
				
			System.out.println("Nome da Regra: "+ regra);  // confirmando o nome da regra
			try {
				
				valores.put(regra, this.calculaScore(res.get(regra)));				
				
			} catch (COrbacException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
		
		this.imprime();
	}
	
	private void imprime(){
		
		Set<String> g = this.valores.keySet();
		
		System.out.println("--------------");
		System.out.println("Regra\tScore");
		System.out.println("--------------");
		
		for(String a : g){
			System.out.println(a+"   -\t"+valores.get(a) );
			System.out.println("--------------");
		}
		
	}
	
	private int calculaScore(CAbstractRule c) throws COrbacException {
		
		role 		= new SubjectsAffecteds().getSubEntity(c.GetRole());
		activity	= new ActionsAffecteds().getSubEntity(c.GetActivity());
		view		= new ObjectsAffecteds().getSubEntity(c.GetView());
		
		int tipo = c.GetType();
		
		if(tipo == 1){  //uma proibição
			
			total = (role * activity *view)*2;
			
		}else{			//para nosso caso, provavelmente permissão ou obrigação
			total = role * activity *view;
		}
		 
		//total = role * activity *view;
		
		return total;
				
	}
	
	//--------------------------------------------------------------------
	//Regras abaixo usadas para auxiliar classes externas	
		
	public String obterScore(String nome){
		
		Integer valor = this.valores.get(nome);
		
		if(valor == null){
			//return "valor não aplicavel";
			return Integer.toString(0);
		}else{
			return Integer.toString(valor);
		}	
		
	}
	
	public String tipoRegra(int tipo){		

		String s="";

		switch(tipo){

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
