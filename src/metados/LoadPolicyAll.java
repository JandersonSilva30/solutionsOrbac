package metados;

import orbac.AbstractOrbacPolicy;
import orbac.COrbacCore;
import orbac.exception.COrbacException;

public final class LoadPolicyAll {

	private COrbacCore core;
	private AbstractOrbacPolicy police;	
	
	private String path = "arquivos/orbac/plugins";
	//private String path_police = "arquivos/examples/escola.pof";
	private String path_police = "arquivos/examples/hospital.pof";
	
	private static final LoadPolicyAll INSTANCE = new LoadPolicyAll();
	
	//construtor
	private LoadPolicyAll() {
		super();
		this.core = COrbacCore.GetTheInstance(this.path);
		
		try {
			
			this.police = core.LoadPolicy(this.path_police);
			
		} catch (COrbacException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public static LoadPolicyAll getInstance() {
		return INSTANCE;
	}

	public AbstractOrbacPolicy getPolice() {
		return police;
	}	
}
