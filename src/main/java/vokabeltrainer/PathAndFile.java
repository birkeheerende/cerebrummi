package vokabeltrainer;

import java.io.File;

public class PathAndFile
{

   String path;
   String file;

   public String getPath()
   {
      return path;
   }

   public void setPath(String path)
   {
      this.path = path;
   }

   public String getFile()
   {
      return file;
   }

   public void setFile(String file)
   {
      this.file = file;
   }

   public String getPathFile()
   {
      return path + File.separator + file;
   }

   public String getPathFileWithZipTest()
   {
      if (file.endsWith(".zip") || file.endsWith(".ZIP"))
      {
         return path + File.separator + file;
      }
      return path + File.separator + file + ".zip";
   }

}
