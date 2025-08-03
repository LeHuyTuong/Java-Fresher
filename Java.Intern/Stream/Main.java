package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Player> players = new ArrayList<>() ;
        players. add(new Player( "Joe Bambrick",  "Northern Ireland", 629)) ;
        players.add(new Player(  "Lionel Messi",  "Argentina", 838));
        players. add(new Player(  "Cristiano Ronaldo", "Portugal", 900));
        players. add(new Player(  "Robert Lewandowski",  "Poland",  634)) ;
        players.add(new Player(  "Romario",  "Brazil", 755));
        players. add(new Player(  "Jimmy Jones",  "Northern Ireland",  648));
        players.add(new Player( "Ferenc Puskas",  "Hungary",  724)) ;
        players.add(new Player(  "Gerd Müller",  "Germany", 634)) ;

            // start con ca
        List<Player> filterPlayers = players.stream() // start Stream cho player vao trong 1 dong suoi
                .filter(player -> player.name.length() > 10) // process Stream() co the process nhieu steam cung 1 luc
                .sorted((p1,p2) -> Integer.compare(p2.goals, p1.goals)) //trong 1 process Stream
                .limit(3) // process Stream()   // phan tu trung gian   -- thit con ca
                .collect(Collectors.toList()); // end Stream()  dung de collect thi   // co co ca de an

        List<Player> filterPlayerAdd10 = players.stream()
                        .map(p -> // dua 1 cau thu ra    // map chuyen doi
                        {
                            p.setGoals(p.getGoals()+10); // cho them 1 ban thang
                            return p;
                        })
                .collect(Collectors.toList()); // lay con ca da dc su ly

        List<String> result = players.stream()
                        .flatMap(p -> Arrays.stream(p.name.split(" "))) // stream trong stream
                        .collect(Collectors.toList());  // thu lay con ga thui

        Integer resultnew = Math.toIntExact(players.stream()
                .flatMap(p -> Arrays.stream(p.name.split(" "))) // stream trong stream
                .count());  // thu lay con ga thui

        System.out.println(resultnew);
    }
}