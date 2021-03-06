/*
 * Copyright or © or Copr. Fabien Michel, Olivier Gutknecht, Jacques Ferber (1997)

fmichel@lirmm.fr
olg@no-distance.net
ferber@lirmm.fr

This software is a computer program whose purpose is to 
provide a lightweight Java library for designing and simulating Multi-Agent Systems (MAS).

This software is governed by the CeCILL-C license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the CeCILL-C
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL-C license and that you accept its terms.
 */
package madkit.roles.manager;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Level;

import org.junit.Test;

import madkit.agr.DefaultMaDKitRoles;
import madkit.kernel.Agent;
import madkit.kernel.Gatekeeper;
import madkit.kernel.JunitMadkit;
import madkit.kernel.Madkit.LevelOption;
import madkit.kernel.Message;
import madkit.testing.util.agent.NormalAgent;

/**
* @author Fabien Michel
*/
public class CandidateTest extends JunitMadkit {
	
	@Test
	public void createGroupHook() {
		addMadkitArgs(LevelOption.agentLogLevel.toString(), Level.ALL.toString()
//				,LevelOption.kernelLogLevel.toString(),Level.ALL.toString()
				);
		launchTest(new NormalAgent() {
			@Override
			protected void activate() {
				launchAgent(new Agent(){
					@Override
					protected void activate() {
						createGroup(COMMUNITY, GROUP,false,new Gatekeeper() {
							@Override
							public boolean allowAgentToTakeRole(String requesterID, String roleName, Object memberCard) {
								return false;
							}
						});
					}
					@Override
					protected void live() {
						getLogger().setLevel(Level.ALL);
						sendReply(waitNextMessage(), new Message());
					}
				});
						sendMessage(COMMUNITY, 
								GROUP, 
								DefaultMaDKitRoles.GROUP_MANAGER_ROLE, 
								new Message());
						pause(10);
						Message m = waitNextMessage();
						assertNotNull(m);
						System.err.println(m);
			}
		});
		pause(100);
	}

}
