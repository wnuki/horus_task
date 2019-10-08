package task;

import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;

@Slf4j
public class MyStructureTest {

    private static int counter = 0;

    private MyStructure compositeStructure = new MyStructure();
    private IMyStructure emptyStructure = new MyStructure();

    private final INode node1 = new Node("code1", "renderer1");
    private final INode node2 = new Node("code2", "renderer2");
    private final INode node3 = new Node("code3", "renderer3");
    private final INode node4 = new Node("code4", "renderer4");
    private final INode node5 = new Node("code5", "renderer5");

    private CompositeNode compositenode1 = new CompositeNode("cCode1", "cRenderer1");
    private CompositeNode compositenode2 = new CompositeNode("cCode2", "cRenderer2");

    @Before
    public void setLog() {
        counter++;
        log.info("Executing test #" + counter);
    }

    @AfterClass
    public static void after() {
        log.info("All tests are finished");
    }

    @Test
    public void shouldReturnNullWhenFindByCodeAndNodesAreEmpty() {
        INode result = emptyStructure.findByCode("testCode");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenFindByRendererAndNodesAreEmpty() {
        INode result = emptyStructure.findByRenderer("testRenderer");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnZeroWhenCountAndNodesAreEmpty() {
        int result = emptyStructure.count();
        assertEquals(0, result);
    }

    @Test
    public void shouldReturnNullWhenNotFoundByCode() {
        setUpStructure();
        INode result = compositeStructure.findByCode("testCode");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenNotFoundByRenderer() {
        setUpStructure();
        INode result = compositeStructure.findByRenderer("testRenderer");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnNodeByCode() {
        setUpStructure();
        INode result = compositeStructure.findByCode("code2");
        assertThat(result, is(node2));
    }

    @Test
    public void shouldReturnNodeByRenderer() {
        setUpStructure();
        INode result = compositeStructure.findByRenderer("code2");
        assertThat(result, is(node2));
    }

    @Test
    public void shouldReturnNumberOfNodes() {
        setUpStructure();
        int result = compositeStructure.count();
        assertEquals(5, result);
    }

    private void setUpStructure() {
        compositenode1.addNode(node1);
        compositenode1.addNode(node2);
        compositenode2.addNode(compositenode1);
        compositenode2.addNode(node3);
        compositeStructure.addNode(compositenode2);
        compositeStructure.addNode(node4);
        compositeStructure.addNode(node5);
    }
}