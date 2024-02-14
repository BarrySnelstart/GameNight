package nl.novi.gamenight.utils;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Component
public class CompressUtil {

    public static byte[] compress(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4*1024];

        try {
            while (!deflater.finished()){
                int size = deflater.deflate(temp);
                byteArrayOutputStream.write(temp, 0, size );
            }
            byteArrayOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }
 public static byte[] decompress(byte[] data){
     Inflater inflater = new Inflater();
     inflater.setInput(data);
     ByteArrayOutputStream outputStream = new ByteArrayOutputStream (data.length);
     byte[] temp = new byte[4*1024];
     try {
         while (!inflater.finished()){
             int count = inflater.inflate(temp);
             outputStream.write(temp, 0, count);
         }
     } catch (DataFormatException e) {
         throw new RuntimeException(e);
     }
     return outputStream.toByteArray();
 }

}
