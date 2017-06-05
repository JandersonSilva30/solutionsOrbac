package exibicao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import control.*;
import metados.LoadPolicyAll;
import orbac.exception.COrbacException;
import orbac.securityRules.CAbstractRule;

public final class Score {	
	
	private static final Score INSTANCE = new Score();
	
	private Map<String, CAbstractRule> abstractRules ;	
	private Map<String, Integer> valores;	
	
	int role, activity, view, total;
	
	private Score(){
		super();		
		valores = new HashMap<String,Integer>() ;
				
		try {
			
			this.abstractRules = LoadPolicyAll.getInstance().getPolice().GetAllAbstractRules(); //obtendo todas as regras abstratas
		
		} catch (COrbacException e) {
			
			e.printStackTrace();
		}	
				
		this.preencheValores();
	}
	
	
	public static Score getInstance() {
		return INSTANCE;
	}

//	public Map<String, Integer> getValores() {
//		return valores;
//	}	

	//Metodo responsavel em preencher a hashmap "valores"
	private void preencheValores(){
		
		Set<String> idRule = this.abstractRules.keySet();     // obtem as chaves do MAP
		
		System.out.println("Quantidade de regras encontradas: "+ abstractRules.size());   //apenas informa o numeor de objetos no MAP
		
		for(String nameRule : idRule){
				
			System.out.println("Nome da Regra: "+ nameRule);  // confirmando o nome da regra
			try {
				
				valores.put(nameRule, this.calculaScore(abstractRules.get(nameRule)));				
				
			} catch (COrbacException e) {
				
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
	
	private int calculaScore(CAbstractRule rule) throws COrbacException {
		
		role 		= new SubjectsAffecteds().getSubEntity(rule.GetRole());
		activity	= new ActionsAffecteds().getSubEntity(rule.GetActivity());
		view		= new ObjectsAffecteds().getSubEntity(rule.GetView());
		
		int tipo = rule.GetType();
		
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

		String typeRule="";

		switch(tipo){

		case 0 : typeRule = "permissão";
		break;

		case 1 : typeRule = "proibição";
		break;

		case 2 : typeRule = "obrigacao";
		break;

		default: typeRule = "Invalid type";
		break;		

		}		
		return typeRule;

	}	
}
