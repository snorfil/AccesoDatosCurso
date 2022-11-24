import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Ejercicio6 {
    File nina;
    File mario;
    File salida;
    public Ejercicio6() {
        mario = new File("images.jpg");
        nina = new File("nina.jpg");
        salida = new File("resultado.jpg");
        crearImagenCombinada(nina,salida,mario);
    }

    private void crearImagenCombinada(File nina,File salida,File mario) {

        try {
            if (!salida.exists()){
                salida.createNewFile();
            }

            FileInputStream fis = new FileInputStream(nina);
            FileOutputStream fos = new FileOutputStream(salida);
            byte[] trozo = new byte[1024];
            int aux;


            for (int i = 0; i < nina.length()/2; i++) {
                aux = fis.read(trozo, 0, trozo.length);
                fos.write(trozo,0,aux);
            }

            fis.close();

            fis = new FileInputStream(mario);
            for (int i = 0; i < nina.length()/2; i++) {
                aux = fis.read(trozo, 0, trozo.length);
                fos.write(trozo,0,aux);
            }
            fis.close();
            fos.close();
        }catch (Exception e){

        }
    }
}
