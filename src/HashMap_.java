import java.util.NoSuchElementException;

public class HashMap_<K, V>
{

    private static class Entry_<K, V> //будет хранить пары "ключ-значение"
    {
        final K key; //не будет изменяться
        V value;
        Entry_<K, V> next; // в случае, если в ведре будет больше одной пары храниться, это будет указателем на следующую


        public Entry_(K key, V value, Entry_<K, V> next) //конструктор с параметрами
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey()
        {
            return key;
        }

        public V getValue()
        {
            return value;
        }


        public void setValue()
        {
            this.value = value;
        }


    }

    private  Entry_<K, V>[] bucket; //массив элементов класса

    private int size;//пользовательский размер массива пар "ключ-значение"
    public static int DEFAULT_CAPACITY = 16; //значение должно являться степенью двойки; я выбрала шестнадцать

    @SuppressWarnings("unchecked")
    public HashMap_()
    {
        bucket = new Entry_[DEFAULT_CAPACITY];
        size = 0;
    }

    public int getHash(K key)
    {
        int hash = key.hashCode();
        return hash % bucket.length;
    }


    public V get(K key) {
        int hashKey = getHash(key);
        for (Entry_<K, V> node = bucket[hashKey]; node != null; node = node.next) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        throw new NoSuchElementException();
    }

    public int size()
    {
        return this.size;
    }

    public void put(K k, V v)
    {
        if(k == null)
        {
            return; //ноль мы не храним
        }

        int hashKey = getHash(k);

        Entry_<K, V> newEntry = new Entry_<K, V>(k, v, null);
        if(bucket[hashKey] == null) //если пусто, перемещаем в новый объект
        {
            bucket[hashKey] = newEntry;
        }
        else
        {
            Entry_<K, V> prev = null;
            Entry_<K, V> cur = bucket[hashKey];

            while (cur != null)
            {
                if (cur.key.equals(k))
                {
                    if (prev == null)

                    {
                        newEntry.next = cur.next;
                        bucket[hashKey] = newEntry;
                        return;
                    } else {
                        newEntry.next = cur.next;
                        prev.next = newEntry;
                        return;
                    }

                }
                prev = cur;
                cur = cur.next;
            }
        }

        size++;
    }

    public static void main(String[] args)
    {
        PutGetIntLongTest();
    }

    static void PutGetIntLongTest(){
        HashMap_ <Integer, Long> hash_map = new HashMap_<Integer, Long >();
        hash_map.put(19, 2993323l);
        hash_map.put(12, 234l);
        hash_map.put(1, 344l);
        hash_map.put(5, 586l);

        System.out.println("Test1: PutGetIntLongTest is started");
        boolean result = true;

        if(hash_map.get(19) != 2993323l)
        {
            System.out.println("hash_map.get(19) != 2993323");
            result = false;
        }else{
            System.out.println("1.1 - OK");
        }
        if(hash_map.get(12) != 234l)
        {
            System.out.println("hash_map.get(12) != 234");
            result = false;
        }else{
            System.out.println("1.2 - OK");
        }
        if(hash_map.get(1) != 344l)
        {
            System.out.println("hash_map.get(1) != 344");
            result = false;
        }else{
            System.out.println("1.3 - OK");
        }
        if(hash_map.get(5) != 586l)
        {
            System.out.println("hash_map.get(5) != 586");
            result = false;
        }else{
            System.out.println("1.4 - OK");
        }
        if(hash_map.size != 4)
        {
            System.out.println("hash_map.size != 4");
            result = false;
        }else{
            System.out.println("1.5 - OK");
        }

        if(result)
            System.out.println("Test1: Passed!");
        else
            System.out.println("Test1: Failed!");
    }



}



