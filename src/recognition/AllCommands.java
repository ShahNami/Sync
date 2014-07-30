package recognition;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import sync.Constants;

/**
 *
 * @author Nami
 */
@Root(name="AllCommands")
public class AllCommands {
    public AllCommands(){}
    @ElementList(name=Constants.commandTag, required=true, inline=true)
    private List<Command> list = new ArrayList<>();
    
    public List<Command> getCommands(){
        return list;
    }
}