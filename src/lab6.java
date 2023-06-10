import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class lab6 {
    public static void main(String[] args){

        int line_num = 0;
        int attempts = 0;
        Address cache_1Array[] = new Address[512];
        int cache_1Hits = 0;
        Address cache_2Array[] = new Address[256];
        int cache_2Hits = 0;
        Address cache_3Array[] = new Address[128];
        int cache_3Hits = 0;
        Address cache_4Array[][] = new Address[256][2];
        int cache_4Hits = 0;
        Address cache_5Array[][] = new Address[128][4];
        int cache_5Hits = 0;
        Address cache_6Array[][] = new Address[32][4];
        int cache_6Hits = 0;
        Address cache_7Array[] = new Address[1024];
        int cache_7Hits = 0;

        try{
            File a = new File(args[0]); //File a = new File(args[0]);
            Scanner scan = new Scanner(a);
            while (scan.hasNextLine()) {
                attempts++;
                String Line = scan.nextLine();
                cache_1Hits += cacheCreateOneDim(cache_1Array, Line, line_num, 1);
                cache_2Hits += cacheCreateOneDim(cache_2Array, Line, line_num, 2);
                cache_3Hits += cacheCreateOneDim(cache_3Array, Line, line_num, 3);
                cache_4Hits += cacheCreateTwoDim(cache_4Array, Line, line_num, 4);
                cache_5Hits += cacheCreateTwoDim(cache_5Array, Line, line_num, 5);
                cache_6Hits += cacheCreateTwoDim(cache_6Array, Line, line_num, 6);
                cache_7Hits += cacheCreateOneDim(cache_7Array, Line, line_num, 7);
                line_num++;
            }
            scan.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred reading file");
            e.printStackTrace();
        }

        double cache_1HitRate = (double)cache_1Hits/attempts * 100;
        double cache_2HitRate = (double)cache_2Hits/attempts * 100;
        double cache_3HitRate = (double)cache_3Hits/attempts * 100;
        double cache_4HitRate = (double)cache_4Hits/attempts * 100;
        double cache_5HitRate = (double)cache_5Hits/attempts * 100;
        double cache_6HitRate = (double)cache_6Hits/attempts * 100;
        double cache_7HitRate = (double)cache_7Hits/attempts * 100;

        printResults(cache_1Hits, cache_1HitRate, 1);
        printResults(cache_2Hits, cache_2HitRate, 2);
        printResults(cache_3Hits, cache_3HitRate, 3);
        printResults(cache_4Hits, cache_4HitRate, 4);
        printResults(cache_5Hits, cache_5HitRate, 5);
        printResults(cache_6Hits, cache_6HitRate, 6);
        printResults(cache_7Hits, cache_7HitRate, 7);
    }

    public static int cacheCreateOneDim(Address[] cacheArray, String address, int line_num, int cache_num) {
        Address addr = setupAddress(address, line_num, cache_num);

        if (cacheArray[addr.getIndex()] == null)
        {
            cacheArray[addr.getIndex()] = addr;
            return 0;
        }
        else if (cacheArray[addr.getIndex()].getTag() == addr.getTag())
        {
            cacheArray[addr.getIndex()] = addr;
            return 1;
        }
        else
        {
            cacheArray[addr.getIndex()] = addr;
            return 0;
        }
    }

    public static int cacheCreateTwoDim(Address[][] cacheArray, String address, int line_num, int cache_num) {
        Address addr = setupAddress(address, line_num, cache_num);

        if (cache_num == 4)
        {
            if (cacheArray[addr.getIndex()][0] == null)
            {
                cacheArray[addr.getIndex()][0] = addr;
                return 0;
            }
            else if (cacheArray[addr.getIndex()][0].getTag() == addr.getTag())
            {
                cacheArray[addr.getIndex()][0] = addr;
                return 1;
            }
            else if (cacheArray[addr.getIndex()][1] == null)
            {
                cacheArray[addr.getIndex()][1] = addr;
                return 0;
            }
            else if (cacheArray[addr.getIndex()][1].getTag() == addr.getTag())
            {
                cacheArray[addr.getIndex()][1] = addr;
                return 1;
            }
            else
            {
                cacheArray[addr.getIndex()][findMinLineTwo(cacheArray[addr.getIndex()][0], cacheArray[addr.getIndex()][1])] = addr;
                return 0;
            }
        }
        else
        {
            if (cacheArray[addr.getIndex()][0] == null)
            {
                cacheArray[addr.getIndex()][0] = addr;
                return 0;
            }
            else if (cacheArray[addr.getIndex()][0].getTag() == addr.getTag())
            {
                cacheArray[addr.getIndex()][0] = addr;
                return 1;
            }
            else if (cacheArray[addr.getIndex()][1] == null)
            {
                cacheArray[addr.getIndex()][1] = addr;
                return 0;
            }
            else if (cacheArray[addr.getIndex()][1].getTag() == addr.getTag())
            {
                cacheArray[addr.getIndex()][1] = addr;
                return 1;
            }
            else if (cacheArray[addr.getIndex()][2] == null)
            {
                cacheArray[addr.getIndex()][2] = addr;
                return 0;
            }
            else if (cacheArray[addr.getIndex()][2].getTag() == addr.getTag())
            {
                cacheArray[addr.getIndex()][2] = addr;
                return 1;
            }
            else if (cacheArray[addr.getIndex()][3] == null)
            {
                cacheArray[addr.getIndex()][3] = addr;
                return 0;
            }
            else if (cacheArray[addr.getIndex()][3].getTag() == addr.getTag())
            {
                cacheArray[addr.getIndex()][3] = addr;
                return 1;
            }
            else
            {
                cacheArray[addr.getIndex()][findMinLineFour(cacheArray[addr.getIndex()][0], cacheArray[addr.getIndex()][1],
                        cacheArray[addr.getIndex()][2], cacheArray[addr.getIndex()][3])] = addr;
                return 0;
            }
        }
    }

    public static int findMinLineTwo(Address addr0, Address addr1)
    {
        if (addr0.getLine_num() < addr1.getLine_num())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    public static int findMinLineFour(Address addr0, Address addr1, Address addr2, Address addr3)
    {
        int min = addr0.getLine_num();
        int indexReplaced = 0;

        if (addr1.getLine_num() < min)
        {
            min = addr1.getLine_num();
            indexReplaced = 1;
        }

        if (addr2.getLine_num() < min)
        {
            min = addr2.getLine_num();
            indexReplaced = 2;
        }

        if (addr3.getLine_num() < min)
        {
            min = addr3.getLine_num();
            indexReplaced = 3;
        }

        return indexReplaced;
    }

    public static Address setupAddress(String address, int line_num, int cache_num)
    {
        int tag = 0;
        int index = 0;
        if (cache_num == 1)
        {
            int addr = Integer.parseInt(address, 16);
            tag = addr >>> 11;
            index = addr >> 2 & 0x1ff;
        }
        else if (cache_num == 2)
        {
            int addr = Integer.parseInt(address, 16);
            tag = addr >>> 11;
            index = addr >> 3 & 0xff;
        }
        else if (cache_num == 3)
        {
            int addr = Integer.parseInt(address, 16);
            tag = addr >>> 11;
            index = addr >> 4 & 0x7f;
        }
        else if (cache_num == 4)
        {
            int addr = Integer.parseInt(address, 16);
            tag = addr >>> 10;
            index = addr >> 2 & 0xff;
        }
        else if (cache_num == 5)
        {
            int addr = Integer.parseInt(address, 16);
            tag = addr >>> 9;
            index = addr >> 2 & 0x7f;
        }
        else if (cache_num == 6)
        {
            int addr = Integer.parseInt(address, 16);
            tag = addr >>> 9;
            index = addr >> 4 & 0x1f;
        }
        else
        {
            int addr = Integer.parseInt(address, 16);
            tag = addr >>> 12;
            index = addr >> 2 & 0x3ff;
        }

        Address complete = new Address(tag, index, line_num);

        return complete;
    }

    public static void printResults(int Hits, double Rate, int cache_num)
    {
        if (cache_num == 1)
        {
            System.out.print("Cache #1\nCache size: 2048B     Associativity: 1" +
                    "     Block size: 1\nHits: " + Hits + "   Hit Rate: ");
            System.out.format("%.2f", Rate);
            System.out.print("%\n");
        }
        else if (cache_num == 2)
        {
            System.out.print("Cache #2\nCache size: 2048B     Associativity: 1" +
                    "     Block size: 2\nHits: " + Hits + "   Hit Rate: ");
            System.out.format("%.2f", Rate);
            System.out.print("%\n");
        }
        else if (cache_num == 3)
        {
            System.out.print("Cache #3\nCache size: 2048B     Associativity: 1" +
                    "     Block size: 4\nHits: " + Hits + "   Hit Rate: ");
            System.out.format("%.2f", Rate);
            System.out.print("%\n");
        }
        else if (cache_num == 4)
        {
            System.out.print("Cache #4\nCache size: 2048B     Associativity: 2" +
                    "     Block size: 1\nHits: " + Hits + "   Hit Rate: ");
            System.out.format("%.2f", Rate);
            System.out.print("%\n");
        }
        else if (cache_num == 5)
        {
            System.out.print("Cache #5\nCache size: 2048B     Associativity: 4" +
                    "     Block size: 1\nHits: " + Hits + "   Hit Rate: ");
            System.out.format("%.2f", Rate);
            System.out.print("%\n");
        }
        else if (cache_num == 6)
        {
            System.out.print("Cache #6\nCache size: 2048B     Associativity: 4" +
                    "     Block size: 4\nHits: " + Hits + "   Hit Rate: ");
            System.out.format("%.2f", Rate);
            System.out.print("%\n");
        }
        else
        {
            System.out.print("Cache #7\nCache size: 4096B     Associativity: 1" +
                    "     Block size: 1\nHits: " + Hits + "   Hit Rate: ");
            System.out.format("%.2f", Rate);
            System.out.print("%\n");
        }

        System.out.println("---------------------------");
    }
}

