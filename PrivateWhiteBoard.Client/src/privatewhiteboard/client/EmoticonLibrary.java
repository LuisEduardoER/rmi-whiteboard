/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package privatewhiteboard.client;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Sherry
 */
public class EmoticonLibrary 
{
    public static ArrayList<String> IdToPath;
    public static final String GIRL = "Girl/Girl-";
    public static final int GIRL_NUM = 43;
    public static final String BOY = "Boy/Boy-";
    public static final int BOY_NUM = 43;
    public static final String CHRISTMAS = "Christmas/Christmas-";
    public static final int CHRISTMAS_NUM = 20;
    public static final String HAPPY = "Happy/Happy-";
    public static final int HAPPY_NUM = 43;
    
    public static void Initialize()
    {
        IdToPath = new ArrayList<String>();
        for (int i = 0; i < GIRL_NUM; i++)
        {
            IdToPath.add("Images/" + GIRL + i + ".png");
        }
        for(int i = 0; i < BOY_NUM; i++)
        {
            IdToPath.add("Images/" + BOY + i + ".png");
        }
        for(int i = 0; i < CHRISTMAS_NUM; i++)
        {
            IdToPath.add("Images/" + CHRISTMAS + i + ".png");
        }       
        for(int i = 0; i < HAPPY_NUM; i++)
        {
            IdToPath.add("Images/" + HAPPY + i + ".png");
        }       
    }
}
