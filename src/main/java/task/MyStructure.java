package task;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@EqualsAndHashCode
@Getter
public class MyStructure implements IMyStructure {
    private List<INode> nodes = new ArrayList<>();

    public void addNode(INode node) {
        nodes.add(node);
    }

    @Override
    public INode findByCode(String code) {
        return findByPredicate(n -> n.getCode().equals(code));
    }

    @Override
    public INode findByRenderer(String renderer) {
        return findByPredicate(n -> n.getRenderer().equals(renderer));
    }

    @Override
    public int count() {
        return flattenNodesList(nodes).size();
    }

    private List<INode> flattenCompositeNode(ICompositeNode node) {
        return new ArrayList<>(node.getNodes());
    }

    private List<INode> flatNodes = new ArrayList<>();
    private List<INode> flattenNodesList(List<INode> nodes) {
        for (INode node : nodes) {
            if (node instanceof ICompositeNode) {
                flattenNodesList(flattenCompositeNode((ICompositeNode) node));
            }
            flatNodes.add(node);
        }
        return flatNodes;
    }

    private INode findByPredicate(Predicate<INode> predicate) {
        return flattenNodesList(nodes).stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }
}
