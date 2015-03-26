import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class AddTest extends TestCase {

    @Test
    public void testAdd() {
        int sum = Add.add(1, 2);
        Assert.assertEquals(sum, 1 + 2);
    }

    @Test
    public void testAdd1() {
        int sum = Add.add(1, 1);
        Assert.assertEquals(sum, 1 + 1);
    }

    @Test
    public void testAdd2() {
        int sum = Add.add(1, 2);
        Assert.assertEquals(sum, 2 + 2);
    }
}