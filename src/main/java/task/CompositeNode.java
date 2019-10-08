package task;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class CompositeNode extends Node implements ICompositeNode {
    private List<INode> nodes;

    public CompositeNode(String code, String renderer) {
        super(code, renderer);
        nodes = new ArrayList<INode>();
    }

    public void addNode(INode node) {
        nodes.add(node);
    }
}
