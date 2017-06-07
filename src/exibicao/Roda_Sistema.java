package exibicao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import metados.LoadSystem;
import orbac.exception.COrbacException;

public class Roda_Sistema {

	public static void main(String[] args) throws COrbacException, Exception {
		
		
		//memoria total
		//long memtotal1 = Runtime.getRuntime().totalMemory();
		
	   //memoria livre		
		//long memLivre1= Runtime.getRuntime().freeMemory();
		
				
		//long initialTime = System.nanoTime();
			
		
		new LoadSystem();
		
		//long tempoTotal= (System.nanoTime() - initialTime);
		
		
		//long memtotal2 = Runtime.getRuntime().totalMemory();
		//long memLivre2= Runtime.getRuntime().freeMemory();
		
		//long memConsumida = memLivre1 - memLivre2;
		
//		String relat = "Memoria Inicial: "+ memtotal1 + 
//						"\nMemoria Livre inicial: "+ memLivre1 +
//						"\n\nTempo Total de execução: " + tempoTotal+
//						"\n\nMemoria Consumida: "+ memConsumida+
//						"\n\nMemoria Total Final: "+ memtotal2+
//						"\nMemoria Livre Final: "+memLivre2;
		
		//JOptionPane.showMessageDialog(null, relat);
		

	}

}
