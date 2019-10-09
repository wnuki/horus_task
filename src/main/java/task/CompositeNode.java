package task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
@ToString
public class CompositeNode implements ICompositeNode {
    private List<INode> nodes = new ArrayList<>();
    private String code;
    private String renderer;

    public CompositeNode(String code, String renderer) {
        this.code = code;
        this.renderer = renderer;
    }

    public void addNode(INode node) {
        nodes.add(node);
    }
}
