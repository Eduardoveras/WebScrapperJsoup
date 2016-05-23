package Main;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Eduardo veras on 19-May-16.
 */
public class Main {

    private static String URL =null;

    public static void main(String[] args) {
        if (getUrl())
        {
            getData();
        }
    }

    private static boolean getUrl(){
        Scanner sc = new Scanner(System.in);
        URL = sc.nextLine().trim();
        System.out.println("The url you typed is: "+ URL);
        String[] schemes = {"http","https"};
        UrlValidator validator = new UrlValidator(schemes);
        if (validator.isValid(URL )) {
            System.out.println("url is valid");
            return true;
        }
        else {
            System.out.println("url is invalid....exiting now");
        }
        return false;
    }

    private static void getData(){
        Document docu = null;
        try {
            docu = Jsoup.connect(URL).get();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("There was an error accesing the URL,Try with another one");
            System.exit(-1);
        }
        Elements elementForms = docu.getElementsByTag("form");
            System.out.println("This is the title: '"+ docu.title()+"'");
            System.out.println("This is the adress: " +docu.location());
            System.out.println("This is the Node Name: " +docu.nodeName());
            System.out.println("This is the line count: " + countLinesBody(docu.html()));
            System.out.println("This is the Paragraph Count: "+ docu.getElementsByTag("p").size());
            System.out.println("This is the Images Count: "+ docu.getElementsByTag("img").size());
            System.out.println("This is the Form Count: "+ docu.getElementsByTag("form").size());
            System.out.println();
            System.out.println("\tProcesing forms....\n");
            for (int i = 0; i < elementForms.size(); i++) {
                System.out.println("\tThis is the form #"+(i+1)+"\n");
                Elements inputlist = elementForms.get(i).getElementsByTag("input");
                for (int j = 0; j < inputlist.size(); j++) {
                    System.out.println("\t\tThis is the input#"+(j+1)+" of the form #" +
                            ""+(i+1));
                    List AttrList = inputlist.get(j).attributes().asList();
                    System.out.println("\t\t\tElement Attributes:");
                    for (Object aAttrList : AttrList) {
                        System.out.println("\t\t\t\t" + aAttrList);
                    }
                }
            }

    }
    private static int countLinesBody(String str){
        String[] lines = str.split("\n" );
        return  lines.length;

    }




}
