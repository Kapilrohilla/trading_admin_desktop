package common;
import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import io.socket.client.IO;
import io.socket.client.Socket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListFiles {
public static Socket socket;


public static ArrayList<String> array=new ArrayList();
public static ArrayList<MtModel> values=new ArrayList();
boolean bbbb=true;

public static void getFeederData()
{
    try {
//                    String name = jTextField1.getText().toString();

                    JSONObject payload = new JSONObject();
//                    payload.put("name", name);
                    payload.put("feederid", "2");
                    

                    OkHttpClient client = new OkHttpClient();
                    String apiUrl = "http://13.233.114.27:8080/get-feeder";//APIs.GET_FEEDER;
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    RequestBody body = RequestBody.create(JSON, payload.toString());

                    Request request = new Request.Builder()
                            .url(apiUrl)
                            .post(body)
                            .build();

                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
//                        JOptionPane.showMessageDialog(DataFeedSymbol.this,"SuccessFully Done"); 
//                        System.out.println("Signup successful");
                        JSONObject json=new JSONObject(response.body().string());
                        JSONArray jsonarray=json.getJSONArray("message");
                        for(int i=0;i<jsonarray.length();i++)
                        {
                            JSONObject jsonobject=jsonarray.getJSONObject(i);
                            String symbol=jsonobject.getString("name");
                            String data4row[] = {symbol};
//                            tableModel.addRow(data4row);
                            array.add(symbol);
                        }
//                        jTable1.setModel(tableModel);
//                        System.out.println("Response: " + response.body().string());
                    } else {
                        System.out.println("Signup Failed" + response.message());
                    }
                } catch (IOException | JSONException ex) {
                    System.out.println("Error occurred while login");
                }

}

public static boolean getData(String name)
{
    for(int i=0;i<array.size();i++)
    {
        String x=array.get(i);
        if(x.equalsIgnoreCase(name))
        {
            return true;
        }
    }
    return false;
}
//public ListFiles()
//{
//    this.bbbb=false;
//}
  public static void main(String args[]){
//      this.bbbb=bbbb;
    getFeederData();
    String folderPath = "C:\\Users\\Lenovo\\AppData\\Roaming\\MetaQuotes\\Terminal\\D0E8209F77C8CF37AD8BF550E51FF075\\MQL5\\Files\\TickData\\"; // Replace with your actual path
    int xx=0;
    File folder = new File(folderPath);
    try{
        URI uri = new URI("http://13.233.114.27:8080/");
     socket = IO.socket(uri);

        // Connect to the server
        socket.connect();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }

    if (folder.isDirectory()) {
      File[] files = folder.listFiles();

      if (files != null) {
        for (File file : files) {
          if (file.isFile()) {
try{
if(getData(file.getName())){
            System.out.println("Imhere"+file.getName());
                ListFiles l=new ListFiles();
                l.simulateRead(folderPath+file.getName(),xx);
                xx++;
                }
                
            }
catch(Exception e)
{
    ListFiles l=new ListFiles();
                l.simulateRead(folderPath+file.getName(),xx);
    e.printStackTrace();
}// Print only file names (not directories)// Print only file names (not directories)
            
          }
        }
      } else {
        System.out.println("Folder is empty or inaccessible.");
      }
    } else {
      System.out.println("Specified path is not a directory.");
    }
  }
  
  class threadone extends Thread{
      String filePath;
      int pp;
      
//       String line;
                    
                   
      public threadone(String filepath,int pp)
      {
          this.filePath=filepath;
          this.pp=pp;
      }
      public void run()
      {try{
          while (true) {
              try{
         FileReader f=new FileReader(filePath);
    try (BufferedReader reader = new BufferedReader(f)) {
      String[] data = reader.lines().toArray(String[]::new);
      if(socket.connected()){
          String line=data[0].substring(2);
          String xz[]=line.split(",");
          
          
          String encodedString = URLEncoder.encode(line, "UTF-8");
          if(!values.get(pp).getBid().equalsIgnoreCase(xz[7]) || !values.get(pp).getAsk().equalsIgnoreCase(xz[9]))
          {
              socket.emit("newMeta", line);
              values.get(pp).setBid(xz[7]);
              values.get(pp).setAsk(xz[9]);
              System.out.println("Simulated read: " + line);
          }
          
      
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
      return;
    }
    f.close();
              }
              catch(Exception e)
              {
//              FileReader f=new FileReader(filePath);
//    try (BufferedReader reader = new BufferedReader(f)) {
//      String[] data = reader.lines().toArray(String[]::new);
//      if(socket.connected()){
//          String encodedstring=data[0].substring(2);
//          socket.emit("newMessage", encodedstring);
//      System.out.println("Simulated read: " + encodedstring);
//      }
//    } catch (IOException xe) {
//      System.err.println("Error reading file: " + xe.getMessage());
//      return;
//    }
//    f.close();    
              }

      // Simulate data access (replace with your actual logic if needed)
       // Print first element for demonstration

      try {
            
        Thread.sleep(50); 
        // Sleep for 5 milliseconds (might be slightly longer)
      
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      }
  }
  
  private void simulateRead(String filepath,int xx) {
      values.add(new MtModel(filepath,"","",""));
//      values.add("");
     threadone thread=new threadone(filepath,xx);
     thread.start();
  }
}
