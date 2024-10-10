import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            String newLine = "";



            while ( (line = reader.readLine()) != null ) {
                if ((line.contains("http") || line.contains("www.")) && line.contains("//")) {
                    int indexOfH = line.indexOf("http");

                    if(indexOfH >= 0){
                        newLine = line.substring(indexOfH);
                    }else{
                        int indexOfWWW = line.indexOf("www.");
                        newLine = line.substring(indexOfWWW);
                    }

                    System.out.println(newLine);

                }
//                if (line.contains("www.")) {
//                    int indexOfW = line.indexOf("www");
//                    String newLineW = line.substring(indexOfW);
//                    System.out.println(newLineW);
//
//                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
