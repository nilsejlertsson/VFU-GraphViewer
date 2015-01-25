/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx.model;

import java.util.Date;

/**
 *
 * @author krille0x7c2
 */
public class Sensor {

    private final String fileName;
    private final Date date;
//    private final String time;
    private final double channel1;
    private final double channel2;
    private final double channel3;
    private final double channel4;
    private final double channel5;
    private final double channel6;
    private final double channel7;
    private final double channel8;

    public Sensor(String fileName, Date date, double channel1, double channel2, double channel3, double channel4, double channel5, double channel6, double channel7, double channel8) {

        this.fileName = fileName;
        this.date = date;
//        this.time = time;
        this.channel1 = channel1;
        this.channel2 = channel2;
        this.channel3 = channel3;
        this.channel4 = channel4;
        this.channel5 = channel5;
        this.channel6 = channel6;
        this.channel7 = channel7;
        this.channel8 = channel8;

    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

//    /**
//     * @return the time
//     */
//    public String getTime() {
//        return time;
//    }

    /**
     * @return the channel1
     */
    public double getChannel1() {
        return channel1;
    }

    /**
     * @return the channel2
     */
    public double getChannel2() {
        return channel2;
    }

    /**
     * @return the channel3
     */
    public double getChannel3() {
        return channel3;
    }

    /**
     * @return the channel4
     */
    public double getChannel4() {
        return channel4;
    }

    /**
     * @return the channel5
     */
    public double getChannel5() {
        return channel5;
    }

    /**
     * @return the channel6
     */
    public double getChannel6() {
        return channel6;
    }

    /**
     * @return the channel7
     */
    public double getChannel7() {
        return channel7;
    }

    /**
     * @return the channel8
     */
    public double getChannel8() {
        return channel8;
    }

}
