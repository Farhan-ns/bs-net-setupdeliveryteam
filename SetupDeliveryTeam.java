/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 *
 * @author Hans
 */
public class SetupDeliveryTeam {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int jml = in.nextInt();
        int[] personil = IntStream.rangeClosed(1, jml).toArray();
        int[] taken = new int[jml];
        int takenIndex = 0;
        
        int[] pref1 = new int[jml];
        int[] pref2 = new int[jml];
        
        //Convert preference from string to integer array
        String pref = in.next();
        for (int i = 0; i < pref.length(); i++) {
            pref1[i] = Character.getNumericValue(pref.charAt(i));
        }
        pref = in.next();
        for (int i = 0; i < pref.length(); i++) {
            pref2[i] = Character.getNumericValue(pref.charAt(i));
        }
        // 
        
        //Each leader pick based on their priority
        for (int i = 0; i < personil.length; i++) {
            int val = getVal(pref1, taken);
            if (val > 0) {
                taken[takenIndex] = val;
                takenIndex++;
            }

            val = getVal(pref2, taken);
            if (val > 0) {
                taken[takenIndex] = val;
                takenIndex++;
            }
        }
        //

        //Assign team number to each personnel based on choices made by both leaders
        for (int i = 1; i <= taken.length; i++) {
            int whichTeam = (i % 2 == 0) ? -2 : -1;

            int id = taken[i - 1];
            for (int j = 0; j < personil.length; j++) {
                if (personil[j] == id) {
                    personil[j] = whichTeam;
                    break;
                }
            }
        }
        for (int i = 0; i < personil.length; i++) {
            personil[i] = (personil[i] == -1) ? 1 : 2;

        }
        //

        System.out.println(Arrays.toString(personil));
    }

    private static int getVal(int[] from, int[] taken) {
        int toReturn = -1;
        boolean exist = false;
        for (int i = 0; i < from.length; i++) {
            int val = from[i];
            for (int a : taken) {
                exist = false;
                if (val == a) {
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                toReturn = val;
                break;
            }
        }
        return toReturn;
    }
}
