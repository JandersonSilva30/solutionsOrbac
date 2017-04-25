package teste;

import afetados.SubjectsAffecteds;
import orbac.AbstractOrbacPolicy;
import orbac.COrbacCore;
import orbac.exception.COrbacException;

public class Teste_Calculo {

	public static void main(String[] args) throws COrbacException, Exception {
		// TODO Auto-generated method stub
		
		// carrgando singleton
		String path = "arquivos/orbac/plugins";		
		COrbacCore core = COrbacCore.GetTheInstance(path);		
		String path_police = "arquivos/examples/hospital.pof";
		
		AbstractOrbacPolicy p = core.LoadPolicy(path_police);
		
		
		
		Calculos calc = new Calculos();
		
		
		
		//System.out.println(calc.contaSubject("specialist", p));
		
		//surgeon
		
		//anesthetist
		
		System.out.println("foram obtidos "+calc.verificaSubRoles("doctor", p)+ " Subjects");
		
		//System.out.println(calc.contaSubject("specialist", p));
		
	}		

}
