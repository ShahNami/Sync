/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package recognition;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import sync.Constants;

/**
 *
 * @author Nami
 */
@Root(name="Command")
public class Command {
    
    public Command(){
        
    }
    
    @Element
    private String name;
    @Element (required=false)
    private String descr = "";
    @ElementList (name=Constants.execTag, required=false, inline=true)
    private List<Execute> execute = new ArrayList<>();
    @ElementList(name=Constants.answerTag, required=false, inline=true)
    private List<Answer> list = new ArrayList<>();
    
    public List<Answer> getAnswers() {
        return list;
    }
    
    public String getName() {
        return name;
    }
    public String getDescr() {
        return descr;
    }
    public List<Execute> getExecute() {
        return execute;
    }
}
