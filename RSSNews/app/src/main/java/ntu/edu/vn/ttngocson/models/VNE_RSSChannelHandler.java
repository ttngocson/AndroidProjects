package ntu.edu.vn.ttngocson.models;

public class VNE_RSSChannelHandler  extends RSSChannelHandle
{
    @Override
    protected void descriptionHandler(String description, RSSItem item)
    {
        String[] strs = description.split(">");
        String des = strs[strs.length-1];
        String link = strs[1];
        int index = link.indexOf("https");
        String linkImg = link.substring(index, link.length()-2);
        item.setImageLink(linkImg);
        item.setDescription(des);
    }
}
