package parsing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Citilink extends Parser {

    Logger log = LoggerFactory.getLogger(Citilink.class);

    private static final Pattern INFO                = Pattern.compile("\"@context\":([\\w\\W]*?)\\}");
    private static final Pattern PRICE               = Pattern.compile("\"price\": \"(.*?)\"");
    private static final Pattern IMAGE               = Pattern.compile("<img.*?class=\" PreviewList__image Image\".*?alt=\".*?\".*?src=\"(.*?)\"");
    private static final Pattern NAME                = Pattern.compile("\"mpn\": \"(.*?)\".*?\"brand\": \"(.*?)\"");
    private static final Pattern PROPERTIES_URL      = Pattern.compile("\"url\": \"(.*?)\"");
    private static final Pattern PROPERTIES          = Pattern.compile("(<div class=\" SpecificationsFull\">[\\w\\W]*?<div class=\"SpecificationsFull\">)");
    private static final Pattern PROPERTIES_SUBGROUP = Pattern.compile("<div class=\" SpecificationsFull\">(.*?)<div class=\" SpecificationsFull\">");
    private static final Pattern PROPERTIES_SUBNAME  = Pattern.compile("<h4.*?>([^<]*?)</h4>");
    private static final Pattern PROPERTIES_SUBPROP  = Pattern.compile("<div class=\"Specifications[^>]*?>([^<]*?)</div>");
    private static final Pattern PRODUCT_CARD        = Pattern.compile("<div class=\"ProductCardVerticalLayout ProductCardVertical__layout\">([\\w\\W]*?)Добавить в корзину");
    private static final Pattern PRODUCT_IMAGE       = Pattern.compile("<div class=\"ProductCardVertical__picture-container \">\\w*?<img.*?src=\"(.*?)\"");
    private static final Pattern PRODUCT_LINK        = Pattern.compile("<div class=\"ProductCardVertical__description \">\\w*?<a.*?href=\"(.*?)\".*?>(.*?)</a>.*?<span class=\"ProductCardVerticalPrice__price-current_current-price js--ProductCardVerticalPrice__price-current_current-price \">(.*?)</span>");

    private static final String SEARCH_URL           = "https://www.citilink.ru/search/?text=";
    private static final String BASE_URL             = "https://www.citilink.ru";

    @Override
    public ArrayList<Product> search(String str) {
        ArrayList<Product> products = new ArrayList<>();
        String res;
        int i = 0;
        try {
            res = SEARCH_URL + URLEncoder.encode(str, StandardCharsets.UTF_8);
            res = getUrlContent(res);
            Matcher matcher = PRODUCT_CARD.matcher(res);
            Matcher m2;
            Matcher m3;
            while (matcher.find()) {
                res = matcher.group(1).replaceAll("\n", "");
                m2 = PRODUCT_LINK.matcher(res);
                m3 = PRODUCT_IMAGE.matcher(res);

                if (m2.find()) {
                    products.add(new Product());
                    products.get(i)
                            .setName(m2.group(2).strip())
                            .setLink(new URL(BASE_URL + m2.group(1).strip()))
                            .setPrice(Integer.parseInt(m2.group(3).replaceAll(" ", "")));
                    if (m3.find()) {
                        products.get(i)
                                .addImageUrl(m3.group(1));
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            log.error("IOException: ", e);
        }

        return products;
    }
}
