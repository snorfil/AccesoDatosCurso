import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio9 {

    /*

        id
        apellido
        dep
        salario

    * */
    Scanner in = new Scanner(System.in);


    public Ejercicio9() {
        System.out.println("Elige una opcion");
        System.out.println("1 -> INSERTAR");
        System.out.println("2 -> CONSULTAR");
        System.out.println("3 -> BORRAR");
        System.out.println("4 -> MODIFICAR");

        int entrada = in.nextInt();
        switch (entrada){
            case 1:
                insertar();
                break;
            case 2:
                consultar();
                break;
            case 3:
                borrar();
                break;
            case 4:
                modificar();
                break;
        }

    }

    public void insertar(){
        int indice;
        DataDTO dto = new DataDTO();
        System.out.println("INSERTAR___________");
        System.out.println("Introduzca apellido");

        String _apellido = in.nextLine();

        for (int i = 0;i<dto.apellido.length;i++)
        {
            dto.apellido[i] = _apellido.charAt(i);
        }

        System.out.println("Introduzca Departamento");

        dto.dep = in.nextInt();

        System.out.println("Introduzca salario");

        dto.salario = in.nextDouble();

        File fichero = new File("datos.dat");


        try {
            if (!fichero.exists()){
                fichero.createNewFile();
            }
            RandomAccessFile file = new RandomAccessFile(fichero,"rw");

            int size = (int) file.length();
            if (size == 0){
                file.writeInt(0);
            }

            file.skipBytes(size);

            file.write(dto.id);
            for (int i = 0; i < dto.apellido.length; i++) {
                file.writeChar(dto.apellido[i]);
            }
            file.writeInt(dto.dep);
            file.writeDouble(dto.salario);

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Escribe el apellido");
        in.nextLine();

        System.out.println("Escribe el departamento");
        in.nextInt();

        System.out.println("Salario");
        in.nextDouble();
    }
    public boolean exist(char[] _apell){
        int size ;
        File fichero = new File("datos.dat");
        try {
            RandomAccessFile file = new RandomAccessFile(fichero, "r");
            size = file.readInt();
            int posicion = Math.toIntExact(file.getFilePointer());
            DataDTO dto = new DataDTO();

            dto.apellido = _apell;
            boolean revision;
            while (!(file.getFilePointer() == file.length())){
                file.skipBytes(posicion+4);
                for (int i = 0; i < dto.apellido.length; i++) {
                    if (file.readChar() == _apell[i]){
                        dto.apellido[i] = _apell[i];
                    }
                }
                file.skipBytes(12);
            }
            file.close();


        }catch (Exception e){

        }
        return false;
    }
    public void consultar() {
            File fichero = new File("datos.dat");
            try {

                RandomAccessFile file = new RandomAccessFile(fichero, "r");
                int posicion ;
                char aux;
                DataDTO dto = new DataDTO();

                posicion = 0;
                for(;;){
                    file.seek(posicion);
                    dto.id = file.readInt();

                    for (int i = 0; i < dto.apellido.length; i++) {
                        aux = file.readChar();
                        dto.apellido[i] = aux;
                    }
                    String apellidos = new String(dto.apellido);
                    dto.dep = file.readInt();
                    dto.salario = file.readDouble();

                    if (file.getFilePointer() == file.length())break;
                }
                file.close();

            }catch (Exception e){

            }
    }
    public void modificar(){

    }
    public void borrar(){

    }
    class DataDTO{
        public int id, dep;
        public Double salario;
        public char apellido[] = new char[10];
    }
}
