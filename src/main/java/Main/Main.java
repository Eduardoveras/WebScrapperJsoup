package Main;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Eduardo veras on 19-May-16.
 */
public class Main {

    private static String URL = "http://www.fb.com";
    private static String Body = null;

    public static void main(String[] args) {
        getUrl();
        getData();

    }

    public static void getUrl(){
        Scanner sc = new Scanner(System.in);
        URL = sc.nextLine().trim();
        System.out.println("The url you typed is: "+ URL);
        String[] schemes = {"http","https"};
        UrlValidator validator = new UrlValidator(schemes);
        if (validator.isValid(URL )) {
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
        Elements elementIMG = docu.getElementsByTag("img");
        Elements elementParagraph = docu.getElementsByTag("p");
        Elements elementForms = docu.getElementsByTag("form");
        if (docu!=null){
            System.out.println("This is the title: '"+ docu.title()+"'");
            System.out.println("This is the adress: " +docu.location());
            System.out.println("This is the Node Name: " +docu.nodeName());
            System.out.println("This is the line count: " + countLinesBody(docu.html()));
            System.out.println("This is the Paragraph Count: "+ elementParagraph.size());
            System.out.println("This is the Images Count: "+ elementIMG.size());
            System.out.println("This is the Form Count: "+ elementForms.size());
            //System.out.println(docu.html());

        }

    }
    private static int countLinesBody(String str){
        String[] lines = str.split("\n" );
        return  lines.length;


    }




}