package com.example.tejas.hosteladmission;
public class Data {
    String _prn;
    String _branch;
    String _phone_number;
    String _presentclass;
    double _pointer;
    public Data(){

    }

    public Data(String _prn, String _branch, String _phone_number, String _presentclass, double _pointer) {
        this._prn = _prn;
        this._branch = _branch;
        this._phone_number = _phone_number;
        this._presentclass = _presentclass;
        this._pointer = _pointer;
    }

    public void set_prn(String _prn) {
        this._prn = _prn;
    }

    public void set_branch(String _branch) {
        this._branch = _branch;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }

    public void set_presentclass(String _presentclass) {
        this._presentclass = _presentclass;
    }

    public void set_pointer(int _pointer) {
        this._pointer = _pointer;
    }

    public String get_prn() {
        return _prn;
    }

    public String get_branch() {
        return _branch;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    public String get_presentclass() {
        return _presentclass;
    }

    public double get_pointer() {
        return _pointer;
    }
}
