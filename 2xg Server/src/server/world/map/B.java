package server.world.map;

import java.io.BufferedReader;
import java.io.FileReader;

public class B
{

    public B()
    {
    }

    public static final void I()
    {
        String s = "";
        int i = 0;
        try
        {
            BufferedReader bufferedreader = null;
            bufferedreader = new BufferedReader(new FileReader("./deps/Data/Data.txt"));
            for(String s1 = bufferedreader.readLine(); s1 != null; s1 = bufferedreader.readLine())
            {
                s1 = s1.trim();
                j[i] = Integer.parseInt(s1);
                i++;
            }

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static int j[] = new int[100];

}

