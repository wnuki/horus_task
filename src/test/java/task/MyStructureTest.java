package task;

import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;

@Slf4j
public class MyStructureTest {

    private static int counter = 0;

    private static MyStructure compositeStructure;
    private static IMyStructure emptyStructure;

    private static final Node node1 = new Node("code1", "renderer1");
    private static final Node node2 = new Node("code2", "renderer2");
    private static final Node node3 = new Node("code3", "renderer3");
    private static final Node node4 = new Node("code4", "renderer4");
    private static final Node node5 = new Node("code5", "renderer5");

    private static CompositeNode compositenode1 = new CompositeNode("cCode1", "cRenderer1");
    private static CompositeNode compositenode2 = new CompositeNode("cCode2", "cRenderer2");

    @BeforeClass
    public static void setUpSNodes() {
        compositenode1.addNode(node1);
        compositenode1.addNode(node2);
        compositenode2.addNode(compositenode1);
        compositenode2.addNode(node3);
    }

    @Before
    public void setUpStructure() {
        counter++;
        log.info("Executing test #" + counter);
        emptyStructure = new MyStructure();
        compositeStructure = new MyStructure();
        compositeStructure.addNode(compositenode2);
        compositeStructure.addNode(node4);
        compositeStructure.addNode(node5);
    }

    @AfterClass
    public static void after() {
        log.info("All tests are finished");
    }

    @Test
    public void shouldReturnNullWhenFindByCodeAndNodesAreEmpty() {
        INode result = emptyStructure.findByCode("code1");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenFindByRendererAndNodesAreEmpty() {
        INode result = emptyStructure.findByRenderer("renderer1");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnZeroWhenCountAndNodesAreEmpty() {
        int result = emptyStructure.count();
        assertEquals(0, result);
    }

    @Test
    public void shouldReturnNullWhenNotFoundByCode() {
        INode result = compositeStructure.findByCode("testCode");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenNotFoundByRenderer() {
        INode result = compositeStructure.findByRenderer("testRenderer");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnNodeByCode() {
        INode result = compositeStructure.findByCode("code2");
        assertThat(result, is(node2));
    }

    @Test
    public void shouldReturnNodeByRenderer() {
        INode result = compositeStructure.findByRenderer("renderer2");
        assertThat(result, is(node2));
    }

    @Test
    public void shouldReturnNumberOfNodes() {
        int result = compositeStructure.count();
        assertEquals(7, result);
    }
}