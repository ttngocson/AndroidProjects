package ntu.edu.vn.ttngocson.models;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public abstract class RSSChannelHandle extends DefaultHandler
{
    ArrayList<RSSItem> listRSSItem = new ArrayList<>();
    String tagName = "";
    StringBuffer stringBuffer = new StringBuffer();
    boolean isNewItem = false;
    int index = -1;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        super.startElement(uri, localName, qName, attributes);
        if(qName.equals("item"))
        {
            RSSItem item = new RSSItem();
            index++;
            listRSSItem.add(item);
            isNewItem = true;
        }
        tagName = qName;
        stringBuffer.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        super.characters(ch, start, length);
        if(isNewItem)
            if(tagName.equals("title") || tagName.equals("description") ||tagName.equals("link"))
                stringBuffer.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        super.endElement(uri, localName, qName);
        if(qName.equals("item"))
            isNewItem = false;
        if(isNewItem)
        {
            if(qName.equals("title"))
                listRSSItem.get(index).setTitle(stringBuffer.toString());
            else if(qName.equals("link"))
                listRSSItem.get(index).setLink(stringBuffer.toString());
            else if(qName.equals("description"))
                descriptionHandler(stringBuffer.toString(), listRSSItem.get(index));

        }
        tagName = "";
    }

    public ArrayList<RSSItem> getListItem(String url)
    {
        try
        {
            HttpsURLConnection connection = ConnectionHelper.getConnection(url);
            InputStream inputStream = connection.getInputStream();
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(inputStream, this);
            inputStream.close();
            connection.disconnect();

        }
        catch (Exception e)
        {
            Log.d("ERROR", "Unknown Error!!!");
        }
        finally
        {
            return listRSSItem;
        }


    }

    protected abstract void descriptionHandler(String description, RSSItem item);


}
