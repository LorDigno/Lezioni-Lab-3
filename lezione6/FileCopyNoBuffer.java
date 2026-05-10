import java.io.*;

public class FileCopyNoBuffer{
    public static void main(String[] args) {
        //nome del file da copiare e del file nuovo
        String inFileStr = args[0]; 
        String outFileStr = args[0] + "-new";

        // for speed benchmarking
        long startTime, elapsedTime;

        InputStream in; OutputStream out;
        int count = 0;
        try{ 
            //associo gli input streams ai file
            //FileInputStream ha un'interfaccia appunto da file allo stream
            in = new FileInputStream(inFileStr);
            out = new FileOutputStream(outFileStr);
            //FileInputStream è una classe concreta che estende InputStream classe astratta

            startTime = System.nanoTime();

            int byteRead;
            // read() rende il byte letto come int
            // read(byte[]) riempie l'array di byte con le letture
            while ((byteRead = in.read()) != -1){ 
                //finche legge byte da in li manda in out
                out.write(byteRead);
                count++;
            }

            elapsedTime = System.nanoTime() - startTime;

            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + "msec");
            System.out.println("File size"+count);

            //manca la close, vedremo dopo il miglior modo per farla
        }
        catch (IOException ex) { ex.printStackTrace();
        }
    }
}