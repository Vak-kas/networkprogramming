package net;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class test3 {
    public static void main(String[] args){
        System.out.println("test");

        try (OutputStream out = new FileOutputStream("./data.txt")){
            generateCharacters(out);
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }


    }

    public static void generateCharacters(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33; //시작
        int numberOfPrintableCharacters = 94; //끝
        int numberOfCharactersPerLine = 72; //라인당 몇개 프린트 할 거냐
        int start = firstPrintableCharacter;
        for (int k=0;k<100;k++) { /* infinite loop->for문으로 변경 */
            for (int i = start; i < start + numberOfCharactersPerLine; i++) {
                out.write(((i - firstPrintableCharacter) % numberOfPrintableCharacters) + firstPrintableCharacter);
            }
            out.write('\r'); // carriage return   -> 줄바꿈 해줄려면 이거가 있어야함 .CRLF 얘기하는 것. 앞쪽으로 땡겨주는 거
            out.write('\n'); // linefeed -> 라인 바꾸는 거

            start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter; // 33번부터 시작하는 게 아니라 34번부터 시작하게
            //이게 끝까지 가는 게 아니라, 끝까지 가면 다시 처음 34번부터 시작되게
        }
    }


    public static void generateCharactersBuf(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharacter;
        byte[] line = new byte[numberOfCharactersPerLine + 2]; // the +2 is for the carriage return and linefeed
        for (int k=0;k<100;k++) { /* infinite loop */
            for (int i = start; i < start + numberOfCharactersPerLine; i++) {
                line[i - start] = (byte) ((i - firstPrintableCharacter)
                        % numberOfPrintableCharacters + firstPrintableCharacter);
            }
            line[72] = (byte) '\r'; // carriage return
            line[73] = (byte) '\n'; // line feed out.write(line);
            out.write(line);
            start = ((start + 1) - firstPrintableCharacter)
                    % numberOfPrintableCharacters + firstPrintableCharacter;
        }
    }

}
