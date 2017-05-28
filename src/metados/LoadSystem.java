package metados;

import javax.swing.JOptionPane;

import exibicao.ExibirScore;

public class LoadSystem {
	
					
	//construtor
	public LoadSystem() throws Exception {
				
		//int resp;
		
		//resp = JOptionPane.showConfirmDialog(null, "deseja continuar?");
		
		//if(resp == JOptionPane.YES_OPTION){
			
			new ExibirScore();
			
			System.err.println("resolvendo");
			
			//Thread.sleep(5000);
			
			new SolveConflicts();
			
		//}else{
			//JOptionPane.showMessageDialog(null, "operação abortada");
			
		//}
		
		
	}
	
	
}
