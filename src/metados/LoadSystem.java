package metados;

import exibicao.ExibirConflitos;
import exibicao.ExibirScore;

public class LoadSystem {
	
					
	//construtor
	public LoadSystem() throws Exception {
					
		new ExibirScore();
			
		
		System.err.println("resolvendo");
		
		Thread.sleep(2000);
		new SolveConflicts();
		
		
		
		
	}
	
	
}
