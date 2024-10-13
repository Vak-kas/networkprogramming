package net;

import java.io.IOException;
import java.io.OutputStream;

public class test0927 {
    public static void main(String[] args){
        System.out.println("test");

        try {
            // 첫 번째 메서드 실행 시간 측정
            long startTime = System.nanoTime();
            generateCharacters(System.out);
            long estimatedTime1 = System.nanoTime() - startTime;


            // 두 번째 메서드 실행 시간 측정
            startTime = System.nanoTime();
            generateCharactersBuf(System.out);
            long estimatedTime2 = System.nanoTime() - startTime;
            System.out.println("generateCharacters 실행 시간: " + estimatedTime1 + " ns");
            System.out.println("generateCharactersBuf 실행 시간: " + estimatedTime2 + " ns");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateCharacters(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33; //시작
        int numberOfPrintableCharacters = 94; //끝
        int numberOfCharactersPerLine = 72; //라인당 몇개 프린트 할 거냐
        int start = firstPrintableCharacter;
        for (int k=0;k<100;k++) { /* infinite loop->for문으로 변경 */
            for (int i = start; i < start + numberOfCharactersPerLine; i++) {
//                out.write(((i - firstPrintableCharacter) % numberOfPrintableCharacters) + firstPrintableCharacter);
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
//            out.write(line);
            start = ((start + 1) - firstPrintableCharacter)
                    % numberOfPrintableCharacters + firstPrintableCharacter;
        }
    }

}
