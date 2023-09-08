/*package parsing;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DNS implements Parser{
    private static final Pattern PRICE = Pattern.compile("\"price\":(\\d+),");
    private static final Pattern DESCRIPTION_ALL = Pattern.compile(
            "<div class=\"product-card-tabs__content\" data-tab=\"description\"" +
            "(.*?)" +
            "<div class=\"product-card-tabs__content\" data-tab=\"communicator\">"
    );
    private static final Pattern BB = Pattern.compile("<div (.*?)<div ");
    private static final Pattern BE = Pattern.compile("<div (.*?)</div");
    private static final Pattern EB = Pattern.compile("</div(.*?)<div ");
    private static final Pattern EE = Pattern.compile("</div(.*?)</div");
    private static final Pattern INFO = Pattern.compile(">([^<]+?)<");
    private static final Pattern NAME = Pattern.compile("\"Модель\":\"(.*?)\"");
    private static final Pattern SCRIPT = Pattern.compile("<script type=\"text/javascript\">([\\w\\W]*)</script>");


    @Override
    public Product parseProduct(File html) throws FileNotFoundException {
        char[] a = new char[(int)html.length() / 2 + 1];
        FileReader reader = new FileReader(html);
        try {
            reader.read(a);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return parseProduct(new String(a));
    }

    @Override
    public Product parseProduct(String str) {
        Product product = new Product();
        int price = -1;

        Matcher matcher = PRICE.matcher(str);
        if (matcher.find())
            price = Integer.parseInt(matcher.group(1));

        product.setPrice(price);
        matcher = DESCRIPTION_ALL.matcher(str);

        if (matcher.find()) {
            String res = "";
            String sub = null;
            Pattern[] patterns = {BB, BE, EB, EE};
            int begin, end, rule, start = -1, offset = 0, old = 0;
            boolean flag = true;

            String temp = matcher.group(1);

            while (!temp.isEmpty()) {
                begin = Integer.MAX_VALUE;
                end   = Integer.MAX_VALUE;

                rule = -1;

                for (int i = 0; i < 4; i++) {
                    matcher = patterns[i].matcher(temp);

                    if (matcher.find()) {
                        if (begin > matcher.start(1)) {
                            begin = matcher.start(1);
                            end   = matcher.end(1);
                            rule = i;
                        } else
                        if ((begin == matcher.start(1)) && (matcher.end(1) < end)) {
                            begin = matcher.start(1);
                            end   = matcher.end(1);
                            rule = i;
                        }
                    }
                }

                if (begin == Integer.MAX_VALUE) break;

                switch (rule) {
                    case 0:
                        offset++;
                        sub = temp.substring(begin, end);
                        temp = temp.substring(end);
                        break;
                    case 1:
                        sub = temp.substring(begin, end + 5);
                        temp = temp.substring(end);
                        break;
                    case 2:
                        sub = temp.substring(begin, end);
                        temp = temp.substring(end);
                        break;
                    case 3:
                        offset--;
                        sub = temp.substring(begin, end);
                        temp = temp.substring(end);
                        break;
                }
                matcher = INFO.matcher(sub);

                if (matcher.find()) {
                    if (start < 0) start = offset;
                    else if (offset <= start) break;


                    if (offset < old) {
                        res += "},\"" + matcher.group(1).replaceAll("\"", "'").strip();
                        while (matcher.find())
                            res += " " + matcher.group(1).replaceAll("\"", "'").strip();
                        res += "\"";
                        flag = true;
                    } else
                    if (offset > old) {
                        res += ":{" + "\"" + matcher.group(1).replaceAll("\"", "'").strip();
                        while (matcher.find())
                            res += " " + matcher.group(1).replaceAll("\"", "'").strip();
                        res += "\"";
                        flag = true;
                    } else
                    if (offset == old) {
                        if (flag) res += ":";
                        else res += ",";

                        res += "\"" + matcher.group(1).replaceAll("\"", "'").strip();
                        while (matcher.find())
                            res += " " + matcher.group(1).replaceAll("\"", "'").strip();

                        res += "\"";
                        flag = !flag;
                    }
                    old = offset;
                    //res += offset + " - " + "\"" +matcher.group(1).replaceAll("\"", "'").strip() + "\"" + "\n";
                }
            }
            res = res.substring(1) + "}}";
            product.setDescription(res);
            matcher = NAME.matcher(res);
            if (matcher.find())
                product.setName(matcher.group(1));
        }

        return product;
    }

    @Override
    public Product parseProduct(URL link) throws IOException, URISyntaxException {
        BufferedReader in = new BufferedReader(new InputStreamReader(link.openStream()));
        Matcher matcher;

        String inputLine, res = "";
        while ((inputLine = in.readLine()) != null)
            res += inputLine + "\n";
        in.close();

        matcher = SCRIPT.matcher(res);
        if (matcher.find())
            res = matcher.group(1);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        System.out.println(res);
        return parseProduct(res);
    }
}
*/