import java.util.*;
class Students implements Comparable<Students>{
    int id;
    String name;
    int mark;
    Students(String name,int mark){
        this.name=name;
        this.mark=mark;
    }
    @Override
    public int compareTo(Students s){
        return s.mark-this.mark;
    }
    @Override
    public String toString(){
        return name+"-"+mark;
    }
}
class ComparableExample{
    public static void main(String[] args) {
        List<Students> students=new ArrayList<>();
        students.add(new Students("A",70));
        students.add(new Students("B",55));
        students.add(new Students("C",85));
        students.add(new Students("D",60));
        Collections.sort(students);
        System.out.println(students);
        for(Students s:students){
            System.out.println(s.name+"-"+s.mark);
        }
    }
}