// Uso de la clase File para mostrar información de ficheros y directorios
 importjava.io.File;
 importjava.io.IOException;

 publicclassListadoDirectorio{
 publicstaticvoidmain(String[]args){
 Stringruta =".";
//    if(args.length>=1){
// ruta = args;
// }
 File fich=newFile(ruta);
 if(!fich.exists()){
 System.out.println("No existe el fichero o directorio ("+ruta +").");
 }else{
 if(fich.isFile()){
 System.out.println(ruta +" es un fichero.");
 }else{
 System.out.println(ruta +" es un directorio. Contenidos: ");
 File[]ficheros=fich.listFiles();// Ojo, ficheros o directorios
 for(File f :ficheros){
 StringtextoDescr=f.isDirectory()?"/":"";
 if(f.isFile()){
 textoDescr+=" ";
 }
 System.out.println(textoDescr +f.getName());
 }}
 }
 }}