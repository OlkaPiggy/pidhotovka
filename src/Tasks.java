import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Tasks {

    //Написати регулярний вираз для пошуку всіх слів, що починаються і
    //закінчуються однаковою літерою без врахування регістру
    static public boolean Task1()
    {
        //?
        String str = "birne";
        return str.matches("^[a-zA-Z][a-zA-Z]+$");
        ////^([a-zA-Z])[xy]*\1$|^[xy]$

    }


    //Описати статичний метод класу, який вхідним параметром має натуральне
    //число, а повертає кількість цифр числа та їх суму.
    static public class resultTask2{
        public int count;
        public int sum;
        public resultTask2()
        {count=0;sum=0;}
    }
    static public resultTask2 Task2(int n )
    {
        resultTask2 r= new resultTask2();
        r.count=0;
        r.sum=0;
        while (n>0)
        {
            r.sum += n%10;
            n=n/10;
            r.count+=1;
        }

        System.out.println(r.sum);
        System.out.println(r.count);
        return r;
    }

    //У файлі задано набір цілих чисел, зчитати їх в колекцію інтерфейсу List,
    //зберігаючи порядок слідування. Знайти всі унікальні елементи колекції.
    static public List<Integer> Task3()
    {
        List<Integer> list=new ArrayList<>();
        try {
            File myObj = new File("read.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] values = data.split(" ");
                for(int i=0;i<values.length;i++)
                {
                    list.add(Integer.parseInt(values[i]));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        ArrayList uniqueList = (ArrayList) list.stream().distinct().collect(Collectors.toList());
        return uniqueList;
    }

    //Описати карту з цілочисельним ключем та списком стрічок за значенням.
    //Створити 3 елементи та видрукувати їх

    static public void Task4(){

        Map map=new HashMap<Integer,String>();
        map.put(1, "First");
        map.put(2, "Second");
        map.put(3, "Third");

        System.out.print(map);
    }

    //Серіалізувати список об’єктів класу, в якому є 2 поля: ім’я персони та дата,
    //попередньо відсортувавши його за датами

    public class Person implements Comparable<Person>, Serializable {
        private String name;
        private LocalDateTime date;

        public Person(String name, LocalDateTime date) {
            this.name = name;
            this.date = date;
        }
        public LocalDateTime getDate() {
            return date;
        }

        @Override
        public int compareTo(Person p) {
            return getDate().compareTo(p.getDate());
        }

    }

    static public void Task5()
    {
        List<Person> people=new ArrayList<>();
       // people.add(new Person("Olha",2002-01-18 00:00));
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getDate))
                .collect(Collectors.toList());

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("result.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            System.out.println(sorted);
            objectOutputStream.writeObject(sorted);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    //У тексті знайти всі числа та вилучити їх тексту.

    static public String Task6(String a)
    {

        a = a.replaceAll("[0-9]", "");
        return a;
    }


    //Дано масив цілих чисел. Поділити цей масив на 3 частини різного розміру.
    //Порахувати суму елементів кожної з частин в окремому потоці. Видрукувати
    //• суму всіх елементів, використовуючи результати окремих потоків

    public class MyThread extends Thread {
        // Конструктор
        MyThread() {
            // Создаём новый поток
            super("Второй поток");
            start(); // Запускаем поток
        }

        public void run(ArrayList<Integer> list,int start,int end) {
            try {
                int sum=0;
                for (int i = start; i <end ; i++) {
                    sum+=list.get(i);
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    public void Task7(File f) {

        List<Integer> list=new ArrayList<>();
        try {
            File myObj = new File("read.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] values = data.split(" ");
                for(int i=0;i<values.length;i++)
                {
                    list.add(Integer.parseInt(values[i]));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int size=list.size()/3;
        final int start=0;
        final int end=size;

        for (int i=0;i<3;i++)
        {
            //new MyThread().start();
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        int sum=0;
                        for (int i = start; i <end ; i++) {
                            sum+=list.get(i);
                        }
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
            });
            t.run();
            //start=end;
            //end+=size;
        }

        MyThread thread=new MyThread();
    }

}
