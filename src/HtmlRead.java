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
                if (line.contains("href"))  {
                    int indexOfBeggining = line.indexOf("href") + 6;
                    newLine = line.substring(indexOfBeggining);
                    int indexOfEnd = newLine.indexOf("\"") ;
                    if (indexOfEnd >= 0){
                        System.out.println(newLine.substring(0, indexOfEnd));
                    } else {
                        indexOfEnd = newLine.indexOf("'");
                        System.out.println(newLine.substring(0, indexOfEnd));

                    }


                }

            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
