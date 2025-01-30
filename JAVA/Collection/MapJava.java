import java.util.*;

public class MapJava {

    public static void main(String[] args) {

        Map<String, Integer> hmap1 = new HashMap<>(); // This is better one
        Map<String, Integer> hmap2 = new HashMap<String,Integer>();

        //Size Operation
        hmap1.put("one", 1);
        System.out.println(hmap1.size());
        hmap1.clear();
        System.out.println(hmap1.isEmpty());

        //Create Operation
        hmap1.put("Apple",1);
        hmap1.put("Orange",2);
        hmap1.put("Watermelon",1);
        hmap1.put("Pear",3);
        //by default it is null:null , we are replacing by null: 0
        hmap1.put(null,0);

        //Read Operation
        // get based on key
        System.out.println(hmap1.get("Apple")); // can throw null
        System.out.println(hmap1.getOrDefault("Orange",-1));
        System.out.println(hmap1.get("Grapes"));
        System.out.println(hmap1.get(null));

        // Checking if value or key exists or not

        System.out.println(hmap1.containsKey("Orange"));
        System.out.println(hmap1.containsValue(3));

        hmap1.remove ("Watermelon");

        // getting whole key sets,
        Set<String> set1 = hmap1.keySet();
        System.out.println(set1);

        // it returns a collection , we can have a list or something typed casted.
        Collection<Integer> list1 = hmap1.values();

        System.out.println(list1);

        System.out.println("Printing entrySet of the HashMap");
        for(Map.Entry<String,Integer> entry : hmap1.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        // another way of iterating like the c#

        hmap1.forEach((k,v)->{ System.out.println(k+":"+v);});

        Map<Integer, String> syncMap = Collections.synchronizedMap(new HashMap<>());


        LinkedHashMap<String, Integer> lmap = new LinkedHashMap<String,Integer>();
        LinkedHashMap<String,Integer> lmap2 = new LinkedHashMap<>();

        TreeMap<Integer,String> treeMap = new TreeMap<>();
        TreeMap<Integer,String> treeMap2 = new TreeMap<Integer,String>();







    }
}
