package task;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class MyStructure implements IMyStructure {
    private List<INode> nodes;

    public MyStructure() {
        nodes = new ArrayList<INode>();
    }

    public INode findByCode(String code) {
        return null;
    }

    public INode findByRenderer(String renderer) {
        return null;
    }

    public int count() {
        return 0;
    }

    public void addNode(INode node) {
        nodes.add(node);
    }
}
