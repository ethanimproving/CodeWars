import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class PaginationHelperTest {

    PaginationHelper<Character> helper = new PaginationHelper<>(Arrays.asList('1','2','3','4','5','6','7','8','9','1','2','3'), 4);

    @Test
    public void itemCountTest(){
        Assertions.assertEquals(helper.itemCount(), 12);
    }

    @Test
    public void pageCountTest(){
        Assertions.assertEquals(helper.pageCount(), 3);
    }

    @Test
    public void pageCountReturnsNumberOfPages(){
        // Arrange
        PaginationHelper<Character> helper = new PaginationHelper<>(Arrays.asList('1','2','3','4','5','6','7'), 3);

        // Assert
        Assertions.assertEquals(3, helper.pageCount());
    }

    @Test
    public void pageItemCountTest1(){
        Assertions.assertEquals(helper.pageItemCount(5), -1);
    }

    @Test
    public void pageItemCountTest2(){
        Assertions.assertEquals(4, helper.pageItemCount(2));
    }

    @Test
    public void pageItemCountTest5(){
        Assertions.assertEquals(4, helper.pageItemCount(0));
    }

    @Test
    public void pageItemCountTest3(){
        List<Character> collection = new ArrayList<>();
        for( int i = 0; i < 44; i++ )
        {
            collection.add( '1' );
        }
        PaginationHelper<Character> helper = new PaginationHelper<>(collection, 15);
        Assertions.assertEquals(14, helper.pageItemCount(3));
    }

    @Test
    public void pageItemCountTest4(){
        List<Character> collection = new ArrayList<>();
        for( int i = 0; i < 24; i++ )
        {
            collection.add( '0' );
        }
        PaginationHelper<Character> helper = new PaginationHelper<>(collection, 10);
        Assertions.assertEquals(4, helper.pageItemCount(3));
    }

    @Test
    public void pageIndexTest1(){
        Assertions.assertEquals(2, helper.pageIndex(4));
    }

    @Test
    public void pageIndexTest2(){
        Assertions.assertEquals(-1, helper.pageIndex(12));
    }

}
