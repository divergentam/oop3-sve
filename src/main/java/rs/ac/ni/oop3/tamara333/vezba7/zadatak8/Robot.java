package rs.ac.ni.oop3.tamara333.vezba7.zadatak8;

import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezba7.WaitUntil;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class Robot implements Runnable{
    final int id;
    final int m;
    final Matrix matrix;
    final List<Character> moves;
    final int color;
    final CountDownLatch countDownLatch;
    final int[] xMoves;
    final int[] yMoves;
    int positionI;
    int positionJ;

    public Robot(int id, int m, final Matrix matrix, final List<Character> moves, int color, CountDownLatch countDownLatch, int positionI, int positionJ) {
        this.id = id;
        this.m = m;
        this.matrix = matrix;
        this.moves = moves;
        this.color = color;
        this.countDownLatch = countDownLatch;
        this.positionI = positionI;
        this.positionJ = positionJ;

       xMoves = new int[moves.size()];
       yMoves = new int[moves.size()];

        for(int i = 0; i < moves.size(); i++){
            switch (moves.get(i)){
                case 'L':
                    xMoves[i] = 0;
                    yMoves[i] = -1;
                    break;
                case 'R':
                    xMoves[i] = 0;
                    yMoves[i] = 1;
                    break;
                case 'U':
                    xMoves[i] = -1;
                    yMoves[i] = 0;
                    break;
                case 'D':
                    xMoves[i] = 1;
                    yMoves[i] = 0;
                    break;
                default:
                    System.err.println("Character " + moves.get(i) + " is not allowed");
            }
        }
    }

    @Override
    public void run() {
        int wantedPositionI;
        int wantedPositionJ;
        int numOfMoves = 0;
        log.info("ROBOT {} started on position matrix[{}][{}]", id, positionI, positionJ);
        for(int i = 0; i< moves.size(); i++){
            WaitUntil.sleep(1000);
            wantedPositionI = positionI + xMoves[i];
            wantedPositionJ = positionJ + yMoves[i];
            log.info("ROBOT {}: I want to move on matrix[{}][{}] ", id, wantedPositionI, wantedPositionJ);
            if(wantedPositionI >= 0 && wantedPositionI < m &&
                wantedPositionJ >= 0 && wantedPositionJ < m) {
                try {
                    if (matrix.allowAccess(wantedPositionI, wantedPositionJ)) {
                        numOfMoves++;
                        positionI = wantedPositionI;
                        positionJ = wantedPositionJ;
                        log.info("ROBOT {} I'm coloring matrix[{}][{}] in {} color", id, positionI, positionJ, color);
                        matrix.matrix[positionI][positionJ] = color;
                        matrix.deniedAccess(positionI, positionJ);
                        log.info("ROBOT {} moved on position matrix[{}][{}]", id, positionI, positionJ);
                    }
                    else{
                        log.error("ROBOT {}: This field is already in use so this robot cant be on that field", id);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i == 3){
                    log.debug("ROBOT {}: I finished {}. moves", id, numOfMoves);
                }
            }
            else{
                log.info("ROBOT {}: I give up on {}. move", id, i);
            }

        }
        countDownLatch.countDown();
    }

}
