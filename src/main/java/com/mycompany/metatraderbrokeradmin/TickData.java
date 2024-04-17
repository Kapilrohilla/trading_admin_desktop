/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

/**
 *
 * @author kapilrohilla
 */
public class TickData{
        String date;
        String volume;
        String bid;
        String ask;
        String ltp;
        String msec;
        String id;

        public TickData(String date, String volume, String bid, String ask, String ltp, String msec,String id) {
            this.date = date;
            this.volume = volume;
            this.bid = bid;
            this.ask = ask;
            this.ltp = ltp;
            this.msec = msec;
            this.id=id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getAsk() {
            return ask;
        }

        public void setAsk(String ask) {
            this.ask = ask;
        }

        public String getLtp() {
            return ltp;
        }

        public void setLtp(String ltp) {
            this.ltp = ltp;
        }

        public String getMsec() {
            return msec;
        }

        public void setMsec(String msec) {
            this.msec = msec;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
        
        
    }