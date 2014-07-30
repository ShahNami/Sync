/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recognition;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import sync.Constants;

/**
 *
 * @author Nami
 */
@Root(name="Execute")
public class Execute {
    
    public Execute(){
        
    }
    @ElementList(name=Constants.paramTag, required=false, inline=true)
    private List<Param> list= new ArrayList<>();
    @Attribute(required=false)
    private String value="";
    
    public String getMethod(){
        return value;
    }
    public List<Param> getParams(){
        return list;
    } 
}
