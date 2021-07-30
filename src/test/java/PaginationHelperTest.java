import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class PaginationHelperTest {

    PaginationHelper<Character> helper = new PaginationHelper<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);

    @Test
    public void itemCountTest(){
        Assertions.assertEquals(helper.itemCount(), 6);
    }

    @Test
    public void pageCountTest(){
        Assertions.assertEquals(helper.pageCount(), 2);
    }

    @Test
    public void pageItemCountTest1(){
        Assertions.assertEquals(4, helper.pageItemCount(0));
    }

    @Test
    public void pageItemCountTest2(){
        Assertions.assertEquals(2, helper.pageItemCount(1));
    }

    @Test
    public void pageItemCountTest3(){
        Assertions.assertEquals(-1, helper.pageItemCount(2));
    }

    @Test
    public void pageIndexTest1(){
        Assertions.assertEquals(1, helper.pageIndex(5));
    }

    @Test
    public void pageIndexTest2(){
        Assertions.assertEquals(0, helper.pageIndex(2));
    }

    @Test
    public void pageIndexTest3(){
        Assertions.assertEquals(-1, helper.pageIndex(20));
    }

    @Test
    public void pageIndexTest4(){
        Assertions.assertEquals(-1, helper.pageIndex(-10));
    }

}
