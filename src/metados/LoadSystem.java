package metados;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import control.SolveConflicts;
import exibicao.ExibirScore;

public class LoadSystem {
	
					
	//construtor
	public LoadSystem() throws Exception {
				
		//int resp;
		
		//resp = JOptionPane.showConfirmDialog(null, "deseja continuar?");
		
		//if(resp == JOptionPane.YES_OPTION){
			
			//long initialTimeScore = System.currentTimeMillis();
			long initialTimeScore = System.nanoTime();
			
			new ExibirScore();
			
			//long finalTimeScore = System.currentTimeMillis()- initialTimeScore;
			long finalTimeScore = (System.nanoTime()- initialTimeScore);//1000000;
			
			//long initialTimeSolve = System.currentTimeMillis();			
			long initialTimeSolve = System.nanoTime();
			System.err.println("resolvendo");
			
			//Thread.sleep(5000);
			
			new SolveConflicts();
			
			//long finalTimeScoreSolve = System.currentTimeMillis()- initialTimeSolve;
			long finalTimeScoreSolve = (System.nanoTime()- initialTimeSolve);//1000000;
			
			
			String relat = finalTimeScore +" "+finalTimeScoreSolve +" "+(finalTimeScore+finalTimeScoreSolve);
			
			System.out.println(relat);
			
			this.escrever(relat);
	
			
			
		//}else{
			//JOptionPane.showMessageDialog(null, "operação abortada");
			
		//}
		
		
	}
	
	private void escrever(String a) {
        File dir = new File("C:\\dados");
        File arq = new File(dir, "dados.txt");

        try {

            //neste ponto criamos o arquivo fisicamente (sugiro criar manualmente e não usar a linha abaixo)
            //arq.createNewFile();

            //Devemos passar no construtor do FileWriter qual arquivo
            // vamos manipular.
            // Esse construtor aceita dois tipos de parâmetros,
            // File ou String.
            //O parâmetro true indica que reescrevemos no arquivo
            // sem apagar o que já existe.
            // O false apagaria o conteúdo do arquivo e escreveria
            // o novo conteúdo.
            // Se não usar o 2° parâmetro, ele por padrão será false.
            //O mais importante, essa linha abre o fluxo do arquivo 
            FileWriter fileWriter = new FileWriter(arq, true);

            //Agora vamos usar a classe PrintWriter para escrever
            //fisicamente no arquivo.
            //Precisamos passar o objeto FileReader em seu construtor
            PrintWriter printWriter = new PrintWriter(fileWriter);

            //Agora vamos escrever no arquivo com o método  println(),
            // que nos permite escrever linha a linha no arquivo
            printWriter.println(a);
            
            //o método flush libera a escrita no arquivo
            printWriter.flush();

            //No final precisamos fechar o arquivo
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
	
}
