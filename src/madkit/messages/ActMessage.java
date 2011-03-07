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
package madkit.messages;

import java.util.Enumeration;
import java.util.Hashtable;

/** This class describes a generic speech act message.

  @author Ol. Gutknecht 10/03/98 original, revision 1.1 04/2002 J.Ferber
  @version 1.1
  @since MadKit 1.0
  */

public class ActMessage extends madkit.kernel.Message
{
	private static final long serialVersionUID = -2556927686645807800L;
	protected String action;
    protected Hashtable<String, Object> fields;
    String content;

    /** Constructor for GenericMessage class */
    public ActMessage(String actiontype)
    {
	    action = actiontype;
	    fields = new Hashtable<String, Object>();
    }

    public ActMessage(String actiontype, String cont)
    {
	    this(actiontype);
            content = cont;
    }

    public ActMessage(String actiontype, Object obj)
    {
	    this(actiontype);
            setObject(obj);
    }

    public ActMessage(String actiontype, String cont, Object obj)
    {
        this(actiontype);
        content = cont;
        setObject(obj);
    }

    public String getAction()
    {
	return action;
    }

    public String getContent()
    {
	    return content;
    }

    public void setContent (String s)
    {
	    content = s;
    }

    public Object getObject()
    {
	return fields.get("object");
    }

    public void setObject (Object s)
    {
	fields.put("object",s);
    }

    public Enumeration<String> getKeys()
    {
	return fields.keys();
    }

    public void setField(String key, Object value)
    {
	fields.put(key,value);
    }
    public Object getFieldValue(String key)
    {
	return fields.get(key);
    }

    public String getInReplyTo(){
        return (String)getFieldValue(":in-reply-to");
    }

    public void setInReplyTo (String s){
        setField(":in-reply-to",s);
    }

}