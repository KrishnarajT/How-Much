import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageDownloader {

    public static void di(String URLst, String filepath){
        try {
            InputStream in = new URL(URLst).openStream();
            Files.copy(in, Paths.get(filepath));
        } catch (Exception e) {
            System.out.println("not found");
//            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws IOException {
//        try {
//            InputStream in = new URL("https://m.media-amaon.com/images/I/71vp8Lec9JL._AC_UL960_FMwebp_QL65_.jpg").openStream();
//            Files.copy(in, Paths.get("./a.jpg"));
//        } catch (Exception e) {
//            System.out.println("not found");
//            throw new RuntimeException(e);
//        }


        URL url = new URL("https://m.media-amazcom/images/I/71vp8Lec9JL._AC_UL960_FMwebp_QL65_.jpg");
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream("./borrowed_image.jpg");
        fos.write(response);
        fos.close();
    }
}
