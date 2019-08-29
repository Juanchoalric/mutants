import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Matrix {

    private boolean isMutant(List<String> list){
        List<List<Character>> adnMatrix = new ArrayList<List<Character>>();
        List<Character> charList = new ArrayList<Character>();

        if(list.size() > 3){
            int counter = 0;
            for (int i=0; i<list.size(); i++){
                char[] charArray = list.get(i).toCharArray();
                for (char output : charArray) {
                    charList.add(output);
                    counter++;
                    if (list.size() == counter){
                        counter = 0;
                        adnMatrix.add(charList);
                        charList = new ArrayList<Character>();
                    }

                }

            }
            int countPattern = 0;
            int countMutant = 0;
            char adn;
            int counterAux;
            char reference;

            for(var i = 0; i<list.size(); i++){
                counterAux = 0;
                reference = adnMatrix.get(i).get(counterAux);
                for(var j = 0; j<list.size(); j++){
                    counterAux++;
                    adn = adnMatrix.get(i).get(j);
                    if (adn == reference){
                        countPattern++;
                    } else {
                        countPattern = 0;
                    }
                    if(countPattern == 4) {
                        countMutant++;
                    }
                        if (countMutant >= 2){
                            return true;
                        }
                }
            }

            countPattern = 1;

            for  (var i = 0; i < list.size(); i++){
                for (var j = 0; j < list.size()-1; j++) {
                        reference = adnMatrix.get(j).get(i);

                    adn = adnMatrix.get(j+1).get(i);
                    if (adn == reference) {
                        countPattern++;

                    }else {
                        countPattern = 0;
                    }
                    if(countPattern == 4) {
                        countMutant++;
                    }
                    if (countMutant >= 2){
                        return true;
                    }
                }
            }

            List<Character> tempLeft = new ArrayList<>();
            List<Character> tempRight = new ArrayList<>();
            countPattern = 1;
            for (int i = adnMatrix.size() - 1; i > 0; i--) {
                for (int j = 0, x = i; x <= adnMatrix.size() - 1; j++, x++) {
                    tempLeft.add(adnMatrix.get(x).get(j));
                }
                if (tempLeft.size() >= 4){
                    for (var k = 0; k<tempLeft.size() - 1; k++){
                        if (tempLeft.get(k) == tempLeft.get(k+1)){
                            countPattern++;
                        }
                        if (countPattern>=4){
                            countMutant++;
                            countPattern=1;
                        }
                        if (countMutant==2){
                            return true;
                        }
                    }
                }
                tempLeft = new ArrayList<>();

            }

            for (int k = 0; k <= adnMatrix.size() - 1; k++) {

                for (int j = 0, y = k; y <= adnMatrix.size() - 1; j++, y++) {
                    tempRight.add(adnMatrix.get(j).get(y));
                }
                if (tempRight.size() > 4){
                    for (var u = 0; u<tempRight.size() - 1; u++){
                        if (tempRight.get(u) == tempRight.get(u+1)){
                            countPattern++;
                        }
                        if (countPattern>=4){
                            countMutant++;
                            countPattern=1;
                        }
                        if (countMutant==2){

                            return true;
                        }
                    }
                }
                tempRight = new ArrayList<>();

            }

        }
        return false;
    }

    public static void main(String[] args){

        List<String> ADN = Stream.of("ACTAGA", "CACTGC", "TGACGT", "TGTACG", "TCTCTA", "GCACTG").collect(Collectors.toList());

        Matrix matrix = new Matrix();
        if(matrix.isMutant(ADN)){
            System.out.println("200");
        } else {
            System.out.println("404");
        }
    }

}

