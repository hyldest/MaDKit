/*
 * Copyright 1997-2011 Fabien Michel, Olivier Gutknecht, Jacques Ferber
 * 
 * This file is part of MadKit.
 * 
 * MadKit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * MadKit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with MadKit. If not, see <http://www.gnu.org/licenses/>.
 */
package madkit.api.abstractAgent;

import madkit.kernel.AbstractAgent;
import madkit.kernel.GroupIdentifier;
import madkit.kernel.JunitMadKit;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import static madkit.kernel.AbstractAgent.ReturnCode.*;

import test.util.JUnitBooterAgent;

/**
 * @author Fabien Michel
 * @since MadKit 5.0.0.7
 * @version 0.9
 * 
 */
@SuppressWarnings("serial")
public class LeaveGroupTest  extends JunitMadKit{
	
	final AbstractAgent target = new AbstractAgent(){
		protected void activate() {
			assertEquals(SUCCESS, createGroup(COMMUNITY,GROUP));
		}
	};

	@Test
	public void nullArgs(){
		launchTest(new AbstractAgent(){
			protected void activate() {
				assertFalse(isCommunity(null));
				assertFalse(isGroup(null,null));
				assertEquals(NOT_COMMUNITY, leaveGroup(null,null));
				assertEquals(NOT_COMMUNITY, leaveGroup(COMMUNITY,null));
				assertEquals(NOT_COMMUNITY, leaveGroup(null,GROUP));
				assertEquals(NOT_COMMUNITY, leaveGroup(aa(),aa()));
			}
		});
	}
	
	@Test
	public void notInGroup(){
		launchTest(new AbstractAgent(){
			protected void activate() {
				launchAgent(target);
				assertTrue(isCommunity(COMMUNITY));
				assertTrue(isGroup(COMMUNITY,GROUP));
				assertEquals(NOT_IN_GROUP, leaveGroup(COMMUNITY,GROUP));
			}
		});
	}
	
	@Test
	public void notGroupNotCommunity(){
		launchTest(new AbstractAgent(){
			protected void activate() {
				assertEquals(SUCCESS, createGroup(COMMUNITY,GROUP));
				assertEquals(NOT_GROUP, leaveGroup(COMMUNITY,aa()));
				assertEquals(NOT_COMMUNITY, leaveGroup(aa(),GROUP));
			}
		});
	}
	
	@Test
	public void leaveGroup(){
		launchTest(new AbstractAgent(){
			protected void activate() {
			assertFalse(isGroup(COMMUNITY,GROUP));
			assertEquals(NOT_COMMUNITY, leaveGroup(COMMUNITY,GROUP));
			assertEquals(SUCCESS, createGroup(COMMUNITY,GROUP));
			assertTrue(isGroup(COMMUNITY,GROUP));
			assertEquals(SUCCESS, leaveGroup(COMMUNITY,GROUP));
			assertFalse(isCommunity(COMMUNITY));
			assertFalse(isGroup(COMMUNITY,GROUP));

			//second run
			assertEquals(SUCCESS, createGroup(COMMUNITY,GROUP));
			assertTrue(isGroup(COMMUNITY,GROUP));
			assertEquals(SUCCESS, leaveGroup(COMMUNITY,GROUP));
			assertFalse(isCommunity(COMMUNITY));
			assertFalse(isGroup(COMMUNITY,GROUP));
		}});
	}

	@Test
	public void killBeforeLeaveGroup(){
		
		launchTest(new AbstractAgent(){
			protected void activate() {
				assertFalse(isCommunity(COMMUNITY));
				launchAgent(target);
				assertEquals(ALREADY_GROUP, createGroup(COMMUNITY,GROUP));
				assertEquals(SUCCESS, target.leaveGroup(COMMUNITY,GROUP));
				assertEquals(SUCCESS, createGroup(COMMUNITY,GROUP));
				assertEquals(NOT_IN_GROUP, target.leaveGroup(COMMUNITY,GROUP));
				assertEquals(SUCCESS, leaveGroup(COMMUNITY,GROUP));
				assertEquals(SUCCESS, target.createGroup(COMMUNITY,GROUP));
				killAgent(target);
				assertFalse(isCommunity(COMMUNITY));
			}
		});
	}
	
}
