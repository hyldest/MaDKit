import madkit.kernel.AbstractAgent;
import madkit.kernel.Madkit;
import madkit.kernel.Madkit.BooleanOption;
import madkit.kernel.Madkit.Option;



public class NoPackage extends AbstractAgent {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		executeThisAgent();
		new Madkit(Option.launchAgents.toString(),AbstractAgent.class.getName()+",true",BooleanOption.desktop.toString());
	}

}
