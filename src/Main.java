import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static String URL = "http://fashionism.com/";
    private static String Body = null;

    public static void main(String[] args) {
        //getUrl();
        getData();

    }

    public static void getUrl(){
        Scanner sc = new Scanner(System.in);
        URL = sc.nextLine();
        System.out.println("The url you typed is: "+ URL);
        String[] schemes = {"http","https"};
        UrlValidator validator = new UrlValidator(schemes);
        if (validator.isValid("ftp://foo.bar.com/")) {
            System.out.println("url is valid");
        }
        else {
            System.out.println("url is invalid");
        }
    }

    private static void getData(){
        Document docu = null;
        try {
            docu = Jsoup.connect(URL).get();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("There was an error accesing the URL,Try with another one");
        }
        Body= docu.body().toString();
        if (docu!=null){
            System.out.println("This is the title: '"+ docu.title()+"'");
            System.out.println("This is the adress: " +docu.location());
            System.out.println("This is the Node Name: " +docu.nodeName());
            System.out.println("This is the line count: " + countLinesBody(docu.body().toString()));
            System.out.println("This is the Paragraph Count: "+ getBodyItemCount("<p"));
            System.out.println("This is the Images Count: "+ getBodyItemCount("<img"));
            System.out.println("This is the Form Count: "+ getBodyItemCount("<form"));
            //System.out.println(Body);

        }

        /*
          .data("query", "Java")
  .userAgent("Mozilla")
  .cookie("auth", "token")
  .timeout(3000)
  .post();
         */
    }
    private static int countLinesBody(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;


    }

    private static int getBodyItemCount(String str){
        if (Body!=null)
            return org.apache.commons.lang3.StringUtils.countMatches(Body,str);
        return 0;
    }



}
