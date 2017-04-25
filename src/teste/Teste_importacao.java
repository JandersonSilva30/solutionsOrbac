package teste;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import exibicao.Score;
import metados.LoadPolicyAll;
import orbac.AbstractOrbacPolicy;
import orbac.COrbacCore;
import orbac.abstractEntities.AbstractEntityHierarchy;
import orbac.concreteEntities.CConcreteEntityAssignement;
import orbac.exception.COrbacException;
import orbac.securityRules.CAbstractRule;

public class Teste_importacao {

	public static void main(String[] args) throws COrbacException, Exception {
		// TODO Auto-generated method stub
		
		// carrgando singleton
		String path = "C:/Users/janderson/Downloads/orbac/plugins";		
		COrbacCore core = COrbacCore.GetTheInstance(path);		
		String path_police = "C:/Users/janderson/Downloads/orbac/examples/hospital.pof";
		
		AbstractOrbacPolicy p = core.LoadPolicy(path_police);
		
		//Calculos calc = new Calculos();
		
		System.out.println(Score.getInstance().getValores().get("i4"));
		
		
		
		/*Map<String, CAbstractRule> res = p.GetAllAbstractRules();
		
		System.out.println("total de :"+ res.size()+ " regras");
		
		Set<String> s = res.keySet();
		
		int cont=0;
		
		for(String a : s){
			System.out.println(res.get(a));
			
			cont++;
			
		}
		
		System.out.println("total de regras "+ cont);
	*/
		
		
		/*
		HashSet<String> res = p.GetViewAssociatedOrganizations("medical_file");
		System.out.println(res.size());
		
		for(String s : res){
			System.out.println(s);
		}*/
		
		//System.out.println(p.GetModificationDate());
		
		
		//FIInferencePolicy f = new FIInferencePolicy((XmlOrbacPolicy) p);
		////f.InferConcretePolicySynchronously();
		//p.SetRule1AboveRule2("p6", "i3", "city_hospital", "city_hospital");
		
		//f.InferConcretePolicySynchronously();
		
		
		 	
		
		//HashSet<CAbstractRule> res = p.GetAbstractPermissions();
		//HashSet<CAbstractRule> res = p.GetAdorbacAbstractPermissions();
		
		/*Set<CRulePriority> res = p.GetRulesPriorities();
		
		for (CRulePriority c : res) {
			System.out.println(c);
		}*/
		
		/*HashSet<String> res = p.GetSubRoles("specialist");
		
		for(String s : res){
			System.out.println(s);
		}*/
		
		//System.out.println(p.GetPolicyAsString());
		
		/*Set<CConcreteEntityAssignement> res = p.GetRolesForSubject("janderson");
			
		for (CConcreteEntityAssignement s : res){
			
			System.out.println(s.GetConcreteEntity());
		}*/
		
		
		
		
	/*	Map<String, CEntityDefinition> res = p.GetRoleDefinitions();
		System.out.println(res);
		 
		Set<String> s = res.keySet();
				
		for(String  c : s){
			
			System.out.println(res.get(c));
		}*/
		
		
		/*Set<String> res = p.GetRolesDefinedInOrganization("city_hospital");
		
		System.out.println("tamanho do resultado: " + res.size());
		
		for (String chave : res){
			
			if (chave != null){
				System.out.println(chave);
				
			}else{
				System.out.println("nao tem nada");
			}			
		}*/
		  
		//HashSet<String> res = p.GetSubRoles("doctor");
		
		/*int contador=0;
		HashSet<String> res = p.GetSubRoles("doctor");		
		
		if(res.size() > 0){
									
			for(String s : res){				
				contador = contador + calc.verificaSubRoles(s, p);
					
			}
				
		}
		
		System.out.println("contador: " + contador);
		
								
				*/
		
		
		
		
		
		
		
		/*Set<AbstractEntityHierarchy> enty = p.GetRoleHierarchies("doctor");
		
		System.out.println("tamano obtido: " + enty.size());
		
		for (AbstractEntityHierarchy c : enty){
			System.out.println(c.GetSubEntity());
		}*/
		
		
		
		
		/*Set<CAbstractConflict> res = p.GetAbstractConflicts();
		
		System.out.println("tamanho do mapa" + res.size());
				
		for (CAbstractConflict chave : res)
		{
			if(chave != null)
				System.out.println(chave.GetFirstRule().GetName());
				System.out.println(chave.GetSecondRule().GetName());
				System.out.println("------------------------------");
		}*/
		
		
		
		/*
		//obtendo os conflitos
		Set<CAbstractConflict> v = p.GetAbstractConflicts();
	
		for(CAbstractConflict c : v){
			
			System.out.println("Id: "+ c.GetFirstRule().hashCode());
			System.out.println("Nome: "+ c.GetFirstRule().GetName());
			System.out.println("tipo: "+ c.GetFirstRule().GetType());
			
			System.out.println("\nEm conflito com\n");
			System.out.println("Id: "+ c.GetSecondRule().hashCode());
			System.out.println("Nome: "+ c.GetSecondRule().GetName());
			System.out.println("tipo: "+ c.GetSecondRule().GetType());
			
			System.out.println("Regra " + c.GetFirstRule().GetName()+ " em conflito com "+ c.GetSecondRule().GetName());
			System.out.println("\n--------------------------------------------------------------------------");
		}
		
		
		String[] res = p.hierarchyViewName;
		System.out.println("odkaskdpo");
		
		for(int i=0; i<res.length; i++){
			System.out.println("posicao " + (i+1) + " = " +res[i] );
		}
		
*/		
		
		
		
		/*
		//regra usada parra verificar se era subecjt ou Role
		if(p.IsRole("doctor")){
			System.out.println("é uma role");
		}else{
			System.out.println("é um subject");
		}
		*/
		
	}

}
