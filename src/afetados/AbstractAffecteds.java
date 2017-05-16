package afetados;

import metados.LoadPolicyAll;
import orbac.AbstractOrbacPolicy;
import orbac.exception.COrbacException;

public abstract class AbstractAffecteds  {
		
	int aux =0;
	AbstractOrbacPolicy p;
	
	
	public AbstractAffecteds() {
		super();		
		this.p = LoadPolicyAll.getInstance().getPolice();
				
	}	
	
	//metodos
	public abstract int getSubEntity(String s) throws COrbacException;
	
	public abstract String getTypeEntity(String s) throws COrbacException;
	
	/**
	 * Esse método em especial verifica se a entidade é Role/ Activity/View ou Subject/action/object 
	 * @param nameEntity
	 * @return
	 * quantidade de objetos concretos afetados
	 * @throws COrbacException
	 * 
	 */
	protected abstract int GetValue(String nameEntity) throws COrbacException;
		

}
