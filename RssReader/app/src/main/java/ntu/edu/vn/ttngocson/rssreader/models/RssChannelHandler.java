package ntu.edu.vn.ttngocson.rssreader.models;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public abstract class RssChannelHandler extends DefaultHandler {

    ArrayList<RssItem> listItem = new ArrayList<>();
    String tagName = "";
    StringBuffer stringBuffer = new StringBuffer();
    boolean isNewItem = false;
    int index = -1;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //super.startElement(uri, localName, qName, attributes);
        if (qName.equals("item")){
            RssItem item = new RssItem();
            index++;
            listItem.add(item);
            isNewItem = true;
        }
        tagName = qName;
        stringBuffer.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //super.characters(ch, start, length);
        if (isNewItem)
            if (tagName.equals("title") || tagName.equals("description") || tagName.equals("link"))
                stringBuffer.append(new String(ch,start,length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //super.endElement(uri, localName, qName);
        if (qName.equals("item"))
            isNewItem = false;
        if(isNewItem){
            if(tagName.equals("title"))
                listItem.get(index).setTitle(stringBuffer.toString());
            else if (tagName.equals("link"))
                listItem.get(index).setLink(stringBuffer.toString());
            else if (tagName.equals("description"))
                descriptionHandler(stringBuffer.toString(), listItem.get(index));

        }
        tagName = "";
    }

    public ArrayList<RssItem> getListItem(String url){
        try{
            HttpsURLConnection connection = ConnectionHelper.getConnection(url);
            InputStream inputStream = connection.getInputStream();
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(inputStream, this);
            inputStream.close();
            connection.disconnect();
        }
        catch (Exception e){
            Log.d("Loi","Loi gi ke me may");
        }
        finally {
            return listItem;
        }
    }

    protected abstract void descriptionHandler(String description, RssItem item);
}
