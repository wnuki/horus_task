package task;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@Getter
public class MyStructure implements IMyStructure {
    private List<INode> nodes = new ArrayList<>();

    public INode findByCode(String code) {
        for (INode node : finalNodes(nodes)) {
            if (node.getCode().equals(code)) {
                return node;
            }
        }
        return null;
    }

    public INode findByRenderer(String renderer) {
        for (INode node : finalNodes(nodes)) {
            if (node.getRenderer().equals(renderer)) {
                return node;
            }
        }
        return null;
    }

    public int count() {
        return finalNodes(nodes).size();

    }

    public void addNode(INode node) {
        nodes.add(node);
    }

    private List<INode> flatNodes(ICompositeNode node) {
        return node.getNodes().stream()
                .collect(Collectors.toList());
    }

    private List<INode> finalNodes = new ArrayList<>();
    private List<INode> finalNodes(List<INode> nodes) {
        for (INode node : nodes) {
            if (node instanceof ICompositeNode) {
                finalNodes(flatNodes((ICompositeNode) node));
            }
            finalNodes.add(node);
        }
        return finalNodes;
    }
}
