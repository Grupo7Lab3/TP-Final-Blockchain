package src.com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesJson <E> {
    private List<E> lista;


     public <T> void writeToJson (String file, List<T> list)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File f = new File(file);
            FileWriter fileWriter = new FileWriter(file);
            SequenceWriter sequenceWriter = mapper.writerWithDefaultPrettyPrinter().writeValuesAsArray(fileWriter);
            sequenceWriter.writeAll(list);
            sequenceWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Hubo un error: " + e.getMessage());
        }
    }
 public void printJsonUser(String file)
    {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User[] userArray= objectMapper.readValue(new File(file),User[].class);
            List<User> personaList = new ArrayList(Arrays.asList(userArray));

            for (User u : personaList)
            {
                System.out.println(u);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<User> readJsonUser(String file)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User[] userArray= objectMapper.readValue(new File(file),User[].class);
            List<User> userList = new ArrayList(Arrays.asList(userArray));

            return userList;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Wallet> readJsonNode(String file)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Wallet[] walletArray= objectMapper.readValue(new File(file),Wallet[].class);
            List<Wallet> node = new ArrayList(Arrays.asList(walletArray));

            return node;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Transfer> readJsonTransfer(String file)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Transfer[] transferArray= objectMapper.readValue(new File(file),Transfer[].class);
            List<Transfer> node = new ArrayList(Arrays.asList(transferArray));

            return node;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
