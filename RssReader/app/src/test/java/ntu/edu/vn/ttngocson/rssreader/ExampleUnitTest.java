package ntu.edu.vn.ttngocson.rssreader;

import org.junit.Test;

import java.util.ArrayList;

import ntu.edu.vn.ttngocson.rssreader.models.RssItem;
import ntu.edu.vn.ttngocson.rssreader.models.VNE_RssHandler;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRssHandler(){
        String url = "https://vnexpress.net/rss/thoi-su.rss";
        VNE_RssHandler handler = new VNE_RssHandler();
        ArrayList<RssItem> list = handler.getListItem(url);
        for (int i = 0; i< list.size(); i++){
            System.out.println("----------------------");
            System.out.println(list.get(i).getTitle());
            System.out.println(list.get(i).getLink());
            System.out.println(list.get(i).getImageLink());
            System.out.println(list.get(i).getDescription());
        }
    }
}