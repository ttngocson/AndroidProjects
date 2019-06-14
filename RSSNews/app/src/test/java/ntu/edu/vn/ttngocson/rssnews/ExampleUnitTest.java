package ntu.edu.vn.ttngocson.rssnews;

import org.junit.Test;

import java.util.ArrayList;

import ntu.edu.vn.ttngocson.models.RSSItem;
import ntu.edu.vn.ttngocson.models.VNE_RSSChannelHandler;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRSSHandler()
    {
        String url = "https://vnexpress.net/rss/thoi-su.rss";
        VNE_RSSChannelHandler handler = new VNE_RSSChannelHandler();
        ArrayList<RSSItem> list = handler.getListItem(url);
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println("*****************************");
            System.out.println(list.get(i).getTitle());
            System.out.println(list.get(i).getLink());
            System.out.println(list.get(i).getImageLink());
            System.out.println(list.get(i).getDescription());

        }
    }
}