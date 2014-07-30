/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package synthesis;

import java.text.DecimalFormat;

/**
 *
 * @author Nami
 */
public class Calculator {
    double _x;
    double _y;
    double _rem;
    double _res;
    String _op;
    public Calculator(){
        _x = 0;
        _y = 0;
        _rem = 0;
        _res = 0;
        _op = "";
        
    }
    
    public double pow(double x, double y){
        _x = x;
        _y = y;
        _res = Math.pow(_x, _y);
        _op = "to the power of";
        return _res;
    }
    
    public double add(double x, double y){
        _x = x;
        _y = y;
        _res = (_x+_y);
        _op = "plus";
        return _res;
    }
    
    public double substract(double x, double y){
        _x = x;
        _y = y;
        _res = (_x-_y);
        _op = "minus";
        return _res;
    }
    
    public double multiply(double x, double y){
        _x = x;
        _y = y;
        _res = (_x*_y);
        _op = "multiplied by";
        return _res;
    }
    
    public double divide(double x, double y){
        _x = x;
        _y = y;
        _op = "divided by";
        if(_y!=0){
            _res = (_x/_y);
            return _res;
        }
        return _res = 0;
    }
    
    @Override
    public String toString(){
        DecimalFormat dfm = new DecimalFormat("0.#");
        //dfm.setDecimalSeparatorAlwaysShown(true);
        if(_op == "divided by" && _y == 0){
            return dfm.format(_x) + ", " + _op + ", " + dfm.format(_y) + ", Sir, this is to complex.";
        }
        return dfm.format(_x) + ", " + _op + ", " + dfm.format(_y) + ", equals, " + dfm.format(_res)+".";
    }
}
