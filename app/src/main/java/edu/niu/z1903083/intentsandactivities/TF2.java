/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 4                  Fall 2022  *
 *   Project Name: Intents and Activities                               *
 *                                                                      *
 *     Class Name: TF2.java                                             *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 10/21/2022                                           *
 *                                                                      *
 *        Purpose: Contains all the necessary information about the 9   *
 *                 classes in Team Fortress 2 organized from strings.xml*
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.intentsandactivities;

public class TF2 {
    public static int n = 0; // For sharing data between activities on which button was currently clicked.
    private static final int[][] data = {
            {R.string.scout_name,    R.string.scout_health,    R.string.scout_speed,    R.string.scout_1,    R.string.scout_2,    R.string.scout_3},
            {R.string.soldier_name,  R.string.soldier_health,  R.string.soldier_speed,  R.string.soldier_1,  R.string.soldier_2,  R.string.soldier_3},
            {R.string.pyro_name,     R.string.pyro_health,     R.string.pyro_speed,     R.string.pyro_1,     R.string.pyro_2,     R.string.pyro_3},
            {R.string.demoman_name,  R.string.demoman_health,  R.string.demoman_speed,  R.string.demoman_1,  R.string.demoman_2,  R.string.demoman_3},
            {R.string.heavy_name,    R.string.heavy_health,    R.string.heavy_speed,    R.string.heavy_1,    R.string.heavy_2,    R.string.heavy_3},
            {R.string.engineer_name, R.string.engineer_health, R.string.engineer_speed, R.string.engineer_1, R.string.engineer_2, R.string.engineer_3},
            {R.string.medic_name,    R.string.medic_health,    R.string.medic_speed,    R.string.medic_1,    R.string.medic_2,    R.string.medic_3},
            {R.string.sniper_name,   R.string.sniper_health,   R.string.sniper_speed,   R.string.sniper_1,   R.string.sniper_2,   R.string.sniper_3},
            {R.string.spy_name,      R.string.spy_health,      R.string.spy_speed,      R.string.spy_1,      R.string.spy_2,      R.string.spy_3}
    };

    public static final void update(int i) {
        n = i;
    }

    public static final int getName() {
        return data[n][0];
    }

    public static final int getHealth() {
        return data[n][1];
    }

    public static final int getSpeed() {
        return data[n][2];
    }

    public static final int getDesc1() {
        return data[n][3];
    }

    public static final int getDesc2() {
        return data[n][4];
    }

    public static final int getDesc3() {
        return data[n][5];
    }
}